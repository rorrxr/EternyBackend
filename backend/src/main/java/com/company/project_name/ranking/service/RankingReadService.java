package com.company.project_name.ranking.service;

import com.company.project_name.external.bser.client.BserApiClient;
import com.company.project_name.external.bser.dto.BserLeaderboardResponseDto;
import com.company.project_name.ranking.dto.RankingEntryDto;
import com.company.project_name.ranking.dto.RankingListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingReadService {

    private static final int DEFAULT_TOP_COUNT = 100;

    private final BserApiClient bserApiClient;

    public RankingListResponseDto getRankingList(int seasonId, int teamMode, int count) {
        BserLeaderboardResponseDto response = bserApiClient.getLeaderboard(seasonId, teamMode, count);
        return buildResponse(response);
    }

    public RankingListResponseDto getTopRanking(int seasonId, int teamMode) {
        BserLeaderboardResponseDto response = bserApiClient.getLeaderboard(seasonId, teamMode, DEFAULT_TOP_COUNT);
        return buildResponse(response);
    }

    private RankingListResponseDto buildResponse(BserLeaderboardResponseDto response) {
        if (response.getCode() != 200 || response.getTopRanks() == null) {
            return RankingListResponseDto.builder()
                    .rankings(List.of())
                    .totalCount(0)
                    .hasMore(false)
                    .build();
        }

        List<RankingEntryDto> rankings = response.getTopRanks().stream()
                .map(RankingEntryDto::fromBser)
                .collect(Collectors.toList());

        return RankingListResponseDto.builder()
                .rankings(rankings)
                .totalCount(rankings.size())
                .hasMore(false)
                .build();
    }
}
