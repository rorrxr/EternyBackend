package com.company.project_name.player.service;

import com.company.project_name.external.bser.client.BserApiClient;
import com.company.project_name.external.bser.dto.*;
import com.company.project_name.external.bser.exception.BserApiException;
import com.company.project_name.global.exception.CustomNotFoundException;
import com.company.project_name.player.dto.*;
import com.company.project_name.player.entity.PlayerProfile;
import com.company.project_name.player.repository.PlayerProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerReadService {

    private final PlayerProfileRepository playerProfileRepository;
    private final PlayerSyncService playerSyncService;
    private final BserApiClient bserApiClient;

    public List<PlayerSearchResultDto> searchByNickname(String nickname) {
        // 1차: 로컬 DB 검색
        List<PlayerProfile> localResults = playerProfileRepository.findByNicknameContainingIgnoreCase(nickname);
        if (!localResults.isEmpty()) {
            return localResults.stream()
                    .map(PlayerSearchResultDto::fromProfile)
                    .collect(Collectors.toList());
        }

        // 2차: BSER API 정확 검색 (닉네임 완전 일치)
        try {
            BserUserNicknameResponseDto bserResponse = bserApiClient.getUserByNickname(nickname);
            if (bserResponse.getCode() == 200 && bserResponse.getUser() != null) {
                BserUserNicknameResponseDto.BserUserInfo userInfo = bserResponse.getUser();
                playerSyncService.upsert(userInfo.getUserNum(), userInfo.getNickname());
                return List.of(PlayerSearchResultDto.fromBser(userInfo));
            }
        } catch (BserApiException e) {
            log.warn("BSER 닉네임 검색 실패 [{}]: {}", nickname, e.getMessage());
        }

        return List.of();
    }

    public PlayerDetailResponseDto getPlayerDetail(long userNum, int seasonId) {
        // 로컬 DB에서 프로필 조회 (없으면 BSER 호출 후 생성)
        PlayerProfile profile = playerProfileRepository.findById(userNum).orElse(null);

        // BSER 실시간 통계 조회
        BserUserStatsResponseDto statsResponse = bserApiClient.getUserStats(userNum, seasonId);
        if (statsResponse.getCode() != 200 || statsResponse.getUserStats() == null || statsResponse.getUserStats().isEmpty()) {
            throw new CustomNotFoundException("플레이어를 찾을 수 없습니다: " + userNum);
        }

        List<BserUserStatsResponseDto.BserSeasonStat> allStats = statsResponse.getUserStats();

        // 솔로(1) 기준으로 tier/rank/mmr 표시
        BserUserStatsResponseDto.BserSeasonStat soloStat = allStats.stream()
                .filter(s -> s.getMatchingTeamMode() == 1)
                .findFirst()
                .orElse(allStats.get(0));

        int totalGames = allStats.stream().mapToInt(BserUserStatsResponseDto.BserSeasonStat::getTotalGames).sum();
        int totalWins  = allStats.stream().mapToInt(BserUserStatsResponseDto.BserSeasonStat::getWins).sum();

        // 최근 매치 조회 (상세 화면용 최근 10경기)
        List<MatchDto> recentMatches = List.of();
        try {
            BserUserGamesResponseDto gamesResponse = bserApiClient.getUserGames(userNum);
            if (gamesResponse.getCode() == 200 && gamesResponse.getUserGames() != null) {
                recentMatches = gamesResponse.getUserGames().stream()
                        .limit(10)
                        .map(MatchDto::fromBserGame)
                        .collect(Collectors.toList());

                // 닉네임 동기화
                if (!recentMatches.isEmpty() && profile == null) {
                    String resolvedNickname = gamesResponse.getUserGames().get(0).getNickname();
                    if (resolvedNickname != null && !resolvedNickname.isBlank()) {
                        playerSyncService.upsert(userNum, resolvedNickname);
                    }
                }
            }
        } catch (BserApiException e) {
            log.warn("최근 매치 조회 실패 [userNum={}]: {}", userNum, e.getMessage());
        }

        String nickname = profile != null ? profile.getNickname()
                : (!recentMatches.isEmpty() ? recentMatches.get(0).getNickname() : "Unknown");

        return PlayerDetailResponseDto.builder()
                .userNum(userNum)
                .nickname(nickname)
                .tier(soloStat.getTier())
                .rank(soloStat.getRank())
                .mmr(soloStat.getMmr())
                .totalGames(totalGames)
                .wins(totalWins)
                .winRate(totalGames > 0 ? (double) totalWins / totalGames * 100 : 0)
                .recentMatches(recentMatches)
                .isFromExternalApi(true)
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }

    public PlayerMatchesResponseDto getPlayerMatches(long userNum) {
        BserUserGamesResponseDto gamesResponse = bserApiClient.getUserGames(userNum);

        if (gamesResponse.getCode() != 200 || gamesResponse.getUserGames() == null) {
            return PlayerMatchesResponseDto.builder()
                    .matches(List.of())
                    .totalCount(0)
                    .hasMore(false)
                    .build();
        }

        List<MatchDto> matches = gamesResponse.getUserGames().stream()
                .map(MatchDto::fromBserGame)
                .collect(Collectors.toList());

        return PlayerMatchesResponseDto.builder()
                .matches(matches)
                .totalCount(matches.size())
                .hasMore(false)
                .build();
    }

    public PlayerRankResponseDto getPlayerRank(long userNum, int seasonId, int teamMode) {
        try {
            BserRankResponseDto rankResponse = bserApiClient.getUserRank(userNum, seasonId, teamMode);
            if (rankResponse.getCode() == 200 && rankResponse.getRank() != null) {
                return PlayerRankResponseDto.fromBser(rankResponse.getRank());
            }
        } catch (BserApiException e) {
            log.warn("랭킹 조회 실패 [userNum={}, season={}, mode={}]: {}", userNum, seasonId, teamMode, e.getMessage());
        }
        return PlayerRankResponseDto.unranked();
    }

    public PlayerStatsResponseDto getPlayerStats(long userNum, int seasonId) {
        BserUserStatsResponseDto statsResponse = bserApiClient.getUserStats(userNum, seasonId);
        return PlayerStatsResponseDto.fromBser(statsResponse);
    }
}
