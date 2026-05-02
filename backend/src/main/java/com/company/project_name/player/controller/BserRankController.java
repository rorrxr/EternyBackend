package com.company.project_name.player.controller;

import com.company.project_name.global.dto.CommonResponse;
import com.company.project_name.player.dto.PlayerRankResponseDto;
import com.company.project_name.player.service.PlayerReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bser")
@RequiredArgsConstructor
public class BserRankController {

    private final PlayerReadService playerReadService;

    @GetMapping("/rank/{userNum}/{season}/{teamMode}")
    public ResponseEntity<CommonResponse<?>> getPlayerRank(
            @PathVariable long userNum,
            @PathVariable int season,
            @PathVariable int teamMode) {
        PlayerRankResponseDto rank = playerReadService.getPlayerRank(userNum, season, teamMode);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(rank));
    }
}
