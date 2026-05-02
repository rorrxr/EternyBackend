package com.company.project_name.match.controller;

import com.company.project_name.global.dto.CommonResponse;
import com.company.project_name.match.dto.MatchDetailResponseDto;
import com.company.project_name.match.service.MatchReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchReadService matchReadService;

    @GetMapping("/{matchId}")
    public ResponseEntity<CommonResponse<?>> getMatchDetail(@PathVariable long matchId) {
        MatchDetailResponseDto detail = matchReadService.getMatchDetail(matchId);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(detail));
    }
}