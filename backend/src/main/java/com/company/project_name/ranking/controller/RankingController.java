package com.company.project_name.ranking.controller;

import com.company.project_name.global.dto.CommonResponse;
import com.company.project_name.ranking.dto.RankingListResponseDto;
import com.company.project_name.ranking.service.RankingReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingReadService rankingReadService;

    @GetMapping
    public ResponseEntity<CommonResponse<?>> getRanking(
            @RequestParam(defaultValue = "25") int season,
            @RequestParam(defaultValue = "1") int teamMode,
            @RequestParam(defaultValue = "100") int count) {
        RankingListResponseDto result = rankingReadService.getRankingList(season, teamMode, count);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(result));
    }

    @GetMapping("/top")
    public ResponseEntity<CommonResponse<?>> getTopRanking(
            @RequestParam(defaultValue = "25") int season,
            @RequestParam(defaultValue = "1") int teamMode) {
        RankingListResponseDto result = rankingReadService.getTopRanking(season, teamMode);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(result));
    }
}
