package com.company.eterny.domain.player.controller;

import com.company.eterny.domain.player.service.PlayerService;
import com.company.eterny.infrastructure.external.bser.dto.PlayerSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 플레이어 검색 API Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    /**
     * 닉네임으로 플레이어 검색
     */
    @GetMapping("/search")
    public ResponseEntity<PlayerSearchResponse> searchPlayer(
            @RequestParam String nickname) {

        log.info("[API] 플레이어 검색 요청: nickname={}", nickname);

        // 입력 검증
        if (nickname == null || nickname.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PlayerSearchResponse response = playerService.searchPlayerByNickname(nickname.trim());

        log.info("[API] 플레이어 검색 응답: nickname={}, foundCount={}",
                nickname, response.getPlayers().size());

        return ResponseEntity.ok(response);
    }

    // TODO: MVP 1차에서는 상세 조회 및 게임 전적 조회 기능 제외 - 추후 구현 예정
    /*
    @GetMapping("/{userNum}")
    public ResponseEntity<PlayerDetailResponse> getPlayerDetail(
            @PathVariable Long userNum,
            @RequestParam(defaultValue = "27") Integer seasonId) {
        // 구현 예정
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userNum}/games") 
    public ResponseEntity<PlayerGamesResponse> getPlayerGames(
            @PathVariable Long userNum,
            @RequestParam(required = false) Integer next,
            @RequestParam(defaultValue = "20") Integer limit) {
        // 구현 예정
        return ResponseEntity.notFound().build();
    }
    */

    /**
     * API 상태 확인
     */
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        boolean isHealthy = playerService.checkApiHealth();

        if (isHealthy) {
            return ResponseEntity.ok("API 정상 작동");
        } else {
            return ResponseEntity.status(503).body("API 연결 불가");
        }
    }
}