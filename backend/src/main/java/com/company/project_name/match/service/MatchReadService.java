package com.company.project_name.match.service;

import com.company.project_name.external.bser.client.BserApiClient;
import com.company.project_name.external.bser.dto.BserGameDetailResponseDto;
import com.company.project_name.global.exception.CustomNotFoundException;
import com.company.project_name.match.dto.MatchDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchReadService {

    private final BserApiClient bserApiClient;

    public MatchDetailResponseDto getMatchDetail(long matchId) {
        BserGameDetailResponseDto detail = bserApiClient.getGameDetail(matchId);
        if (detail.getCode() != 200 || detail.getUserGames() == null || detail.getUserGames().isEmpty()) {
            throw new CustomNotFoundException("매치를 찾을 수 없습니다: " + matchId);
        }
        return MatchDetailResponseDto.fromBser(detail, matchId);
    }
}
