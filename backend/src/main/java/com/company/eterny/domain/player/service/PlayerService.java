package com.company.eterny.domain.player.service;

import com.company.eterny.infrastructure.external.bser.dto.BserUserDetailDto;
import com.company.eterny.infrastructure.external.bser.dto.NicknameDto;
import com.company.eterny.infrastructure.external.bser.service.BserExternalService;
import com.company.eterny.domain.player.entity.Player;
import com.company.eterny.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 플레이어 관련 비즈니스 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final BserExternalService bserExternalService;

    /**
     * 닉네임으로 플레이어 검색 (BSER API + 로컬 저장)
     * @param nickname 검색할 닉네임
     * @return 검색된 플레이어 목록
     */
    @Transactional
    public List<NicknameDto> searchPlayer(String nickname) {
        log.info("플레이어 검색 - nickname: {}", nickname);
        
        try {
            List<NicknameDto> results = bserExternalService.searchUserByNickname(nickname);
            
            if (!results.isEmpty()) {
                // 검색 결과를 로컬 DB에 저장/업데이트
                savePlayersToLocal(results);
                log.info("플레이어 검색 완료 - {}개 결과", results.size());
            } else {
                log.warn("플레이어 검색 결과 없음 - nickname: {}", nickname);
            }
            
            return results;
            
        } catch (Exception e) {
            log.error("플레이어 검색 실패 - nickname: {}, error: {}", nickname, e.getMessage(), e);
            throw new RuntimeException("플레이어 검색 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 유저 번호로 플레이어 상세 정보 조회
     * @param userNum 유저 번호
     * @return 플레이어 상세 정보
     */
    @Cacheable(value = "playerDetail", key = "#userNum")
    public Optional<Player> getPlayerDetail(Long userNum) {
        log.info("플레이어 상세 정보 조회 - userNum: {}", userNum);
        
        Optional<Player> localPlayer = playerRepository.findById(userNum);
        
        if (localPlayer.isPresent()) {
            return localPlayer;
        }
        
        // 로컬에 없으면 외부 API에서 조회 후 저장
        try {
            BserUserDetailDto userDetail = bserExternalService.getUserDetail(userNum);
            if (userDetail != null) {
                Player newPlayer = Player.builder()
                        .userNum(userNum)
                        .nickname(userDetail.getNickname())
                        .build();
                
                Player savedPlayer = playerRepository.save(newPlayer);
                log.info("새 플레이어 정보 저장 완료 - userNum: {}", userNum);
                return Optional.of(savedPlayer);
            }
        } catch (Exception e) {
            log.error("외부 API에서 플레이어 정보 조회 실패 - userNum: {}, error: {}", userNum, e.getMessage());
        }
        
        return Optional.empty();
    }

    /**
     * 플레이어 랭킹 조회
     * @param pageable 페이징 정보
     * @param tier 티어 필터 (선택적)
     * @return 페이징된 플레이어 랭킹
     */
    public Page<Player> getRanking(Pageable pageable, String tier) {
        log.info("플레이어 랭킹 조회 - page: {}, size: {}, tier: {}", 
                pageable.getPageNumber(), pageable.getPageSize(), tier);
        
        if (tier != null && !tier.trim().isEmpty()) {
            return playerRepository.findByTierOrderByMmrDesc(tier.trim(), pageable);
        }
        
        return playerRepository.findAllByOrderByMmrDesc(pageable);
    }

    /**
     * 플레이어 정보 강제 새로고침
     * @param userNum 유저 번호
     * @return 업데이트된 플레이어 정보
     */
    @Transactional
    public Optional<Player> refreshPlayerInfo(Long userNum) {
        log.info("플레이어 정보 새로고침 - userNum: {}", userNum);
        
        try {
            BserUserDetailDto userDetail = bserExternalService.getUserDetail(userNum);
            if (userDetail == null) {
                log.warn("외부 API에서 플레이어 정보를 찾을 수 없음 - userNum: {}", userNum);
                return Optional.empty();
            }
            
            Optional<Player> existingPlayer = playerRepository.findById(userNum);
            Player player;
            
            if (existingPlayer.isPresent()) {
                // 기존 플레이어 정보 업데이트
                player = existingPlayer.get();
                player.updateFromBser(userDetail.getNickname(), null, null);
            } else {
                // 새 플레이어 생성
                player = Player.builder()
                        .userNum(userNum)
                        .nickname(userDetail.getNickname())
                        .build();
            }
            
            Player savedPlayer = playerRepository.save(player);
            log.info("플레이어 정보 새로고침 완료 - userNum: {}", userNum);
            return Optional.of(savedPlayer);
            
        } catch (Exception e) {
            log.error("플레이어 정보 새로고침 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            throw new RuntimeException("플레이어 정보 새로고침 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 검색 결과를 로컬 DB에 저장/업데이트
     * @param players 저장할 플레이어 목록
     */
    @Transactional
    protected void savePlayersToLocal(List<NicknameDto> players) {
        log.info("플레이어 정보 로컬 저장 시작 - {}개", players.size());
        
        for (NicknameDto data : players) {
            try {
                Optional<Player> existing = playerRepository.findById(data.getUserNum());

                if (existing.isPresent()) {
                    // 기존 플레이어 업데이트
                    Player player = existing.get();
                    player.updateFromBser(data.getNickname(), null, null);
                    playerRepository.save(player);
                    log.debug("플레이어 정보 업데이트 - userNum: {}, nickname: {}", 
                            data.getUserNum(), data.getNickname());
                } else {
                    // 새 플레이어 생성
                    Player newPlayer = Player.builder()
                            .userNum(data.getUserNum())
                            .nickname(data.getNickname())
                            .build();
                    playerRepository.save(newPlayer);
                    log.debug("새 플레이어 저장 - userNum: {}, nickname: {}", 
                            data.getUserNum(), data.getNickname());
                }
            } catch (Exception e) {
                log.error("플레이어 저장 실패 - userNum: {}, error: {}", data.getUserNum(), e.getMessage());
                // 개별 저장 실패 시에도 다른 플레이어들은 계속 처리
            }
        }
        
        log.info("플레이어 정보 로컬 저장 완료");
    }

    /**
     * 전체 플레이어 수 조회
     * @return 전체 플레이어 수
     */
    public long getTotalPlayerCount() {
        return playerRepository.count();
    }

    /**
     * 특정 티어의 플레이어 수 조회
     * @param tier 티어
     * @return 해당 티어의 플레이어 수
     */
    public long getPlayerCountByTier(String tier) {
        return playerRepository.countByTier(tier);
    }
}
