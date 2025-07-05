package com.company.eterny.player.service;

import com.company.eterny.bser.dto.NicknameData;
import com.company.eterny.bser.service.BserService;
import com.company.eterny.player.entity.Player;
import com.company.eterny.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final BserService bserService;

    /** 플레이어 검색 (BSER API + 로컬 저장) */
    public List<NicknameData> searchPlayer(String nickname) {
        try {
            List<NicknameData> results = bserService.getUserByNickname(nickname);
            if (!results.isEmpty()) {
                savePlayersToLocal(results);
            }
            return results;
        } catch (Exception e) {
            log.error("[searchPlayer] 닉네임 검색 실패: {}", e.getMessage(), e);
            throw e;
        }
    }

    /** 플레이어 상세 정보 */
    public Optional<Player> getPlayerDetail(Long userNum) {
        return playerRepository.findById(userNum);
    }

    /** 랭킹 조회 */
    public Page<Player> getRanking(Pageable pageable, String tier) {
        if (tier != null && !tier.isEmpty()) {
            return playerRepository.findByTierOrderByMmrDesc(tier, pageable);
        }
        return playerRepository.findAllByOrderByMmrDesc(pageable);
    }

    /** 로컬 DB에 플레이어 저장/업데이트 */
    @Transactional
    public void savePlayersToLocal(List<NicknameData> players) {
        for (NicknameData data : players) {
            Optional<Player> existing = playerRepository.findById(data.getUserNum());

            if (existing.isPresent()) {
                // 기존 플레이어 업데이트
                Player player = existing.get();
                player.updateFromBser(data.getNickname(), null, null);
                playerRepository.save(player);
            } else {
                // 새 플레이어 생성
                Player newPlayer = Player.builder()
                        .userNum(data.getUserNum())
                        .nickname(data.getNickname())
                        .build();
                playerRepository.save(newPlayer);
            }
        }
    }
}