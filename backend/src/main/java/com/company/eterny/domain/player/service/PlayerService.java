package com.company.eterny.domain.player.service;

import com.company.eterny.domain.player.dto.PlayerDto;
import com.company.eterny.infrastructure.external.bser.service.BserApiService;
import com.company.eterny.infrastructure.external.bser.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 플레이어 관련 비즈니스 로직 서비스 (MVP 버전)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final BserApiService bserApiService;

    /**
     * 닉네임으로 플레이어 검색
     */
    public PlayerSearchResponse searchPlayerByNickname(String nickname) {
        log.info("[SERVICE] 플레이어 검색: nickname={}", nickname);

        try {
            // BSER API 호출
            List<BserUserDto> users = bserApiService.getUserByNickname(nickname);

            // 응답 DTO 변환
            List<PlayerDto.Summary> players = users.stream()
                    .map(this::convertToPlayerSummary)
                    .toList();

            log.info("[SERVICE] 플레이어 검색 완료: nickname={}, foundCount={}",
                    nickname, players.size());

            return PlayerSearchResponse.builder()
                    .searchKeyword(nickname)
                    .players(players)
                    .totalCount(players.size())
                    .build();

        } catch (Exception e) {
            log.error("[SERVICE] 플레이어 검색 실패: nickname={}, error={}", nickname, e.getMessage());
            throw new RuntimeException("플레이어 검색 중 오류가 발생했습니다.", e);
        }
    }

    // TODO: MVP 1차에서는 상세 조회 기능 제외 - 추후 구현 예정
    /*
    public PlayerDetailResponse getPlayerDetail(Long userNum, Integer seasonId) {
        // 구현 예정
        return null;
    }

    public PlayerGamesResponse getPlayerGames(Long userNum, Integer next, Integer limit) {
        // 구현 예정  
        return null;
    }
    */

    /**
     * API 상태 확인
     */
    public boolean checkApiHealth() {
        return bserApiService.isApiHealthy();
    }

    // ==================== DTO 변환 메서드 ====================

    private PlayerDto.Summary convertToPlayerSummary(BserUserDto user) {
        return PlayerDto.Summary.builder()
                .userNum(user.getUserNum())
                .nickname(user.getNickname())
                .mmr(0)  // 기본값
                .tierLevel("UNRANKED")  // 기본값
                .winRate(0.0)  // 기본값
                .totalGames(0)  // 기본값
                .mostCharacterName("")  // 기본값
                .isActive(true)  // 기본값
                .build();
    }
}