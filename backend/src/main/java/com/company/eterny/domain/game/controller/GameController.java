package com.company.eterny.domain.game.controller;

import com.company.eterny.domain.game.dto.GameDto;
import com.company.eterny.domain.game.dto.PageResponse;
import com.company.eterny.domain.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    /**
     * 플레이어 게임 기록 조회 (페이징, 필터 지원)
     */
    @GetMapping("/player/{userNum}")
    public PageResponse<GameDto.Response> getPlayerGames(
            @PathVariable Long userNum,
            @ModelAttribute GameDto.SearchRequest request
    ) {
        return gameService.getPlayerGames(userNum, request);
    }
}
