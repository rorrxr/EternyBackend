package com.company.project_name.player.controller;

import com.company.project_name.global.dto.CommonResponse;
import com.company.project_name.player.dto.*;
import com.company.project_name.player.service.PlayerReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerReadService playerReadService;

    @GetMapping("/players/search")
    public ResponseEntity<CommonResponse<?>> searchPlayers(@RequestParam String nickname) {
        List<PlayerSearchResultDto> results = playerReadService.searchByNickname(nickname);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(results));
    }

    @GetMapping("/players/{userNum}")
    public ResponseEntity<CommonResponse<?>> getPlayerDetail(
            @PathVariable long userNum,
            @RequestParam(defaultValue = "25") int season) {
        PlayerDetailResponseDto detail = playerReadService.getPlayerDetail(userNum, season);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(detail));
    }

    @GetMapping("/players/{userNum}/matches")
    public ResponseEntity<CommonResponse<?>> getPlayerMatches(@PathVariable long userNum) {
        PlayerMatchesResponseDto matches = playerReadService.getPlayerMatches(userNum);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(matches));
    }

    @GetMapping("/stats/user/{userNum}")
    public ResponseEntity<CommonResponse<?>> getPlayerStats(
            @PathVariable long userNum,
            @RequestParam(defaultValue = "25") int season) {
        PlayerStatsResponseDto stats = playerReadService.getPlayerStats(userNum, season);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(stats));
    }
}
