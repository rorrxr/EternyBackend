package com.company.eterny.global.health;

import com.company.eterny.global.dto.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 서버 상태 확인용 컨트롤러
 */
@Tag(name = "Health Check", description = "서버 상태 확인 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @Value("${spring.application.name:Eterny}")
    private String applicationName;

    @Operation(summary = "서버 상태 확인", description = "서버가 정상적으로 실행 중인지 확인합니다.")
    @GetMapping
    public ResponseEntity<CommonResponse<Map<String, Object>>> healthCheck() {
        
        log.info("헬스체크 요청 수신");
        
        Map<String, Object> healthInfo = new HashMap<>();
        healthInfo.put("status", "UP");
        healthInfo.put("application", applicationName);
        healthInfo.put("timestamp", LocalDateTime.now());
        healthInfo.put("message", "🎮 Eterny 서버가 정상적으로 실행 중입니다!");
        
        return ResponseEntity.ok(
            new CommonResponse<>(200, "서버 정상", healthInfo)
        );
    }

    @Operation(summary = "간단한 ping 테스트", description = "간단한 응답 테스트")
    @GetMapping("/ping")
    public ResponseEntity<CommonResponse<String>> ping() {
        
        log.info("Ping 요청 수신");
        
        return ResponseEntity.ok(
            new CommonResponse<>(200, "Success", "pong 🏓")
        );
    }

    @Operation(summary = "API 목록", description = "사용 가능한 주요 API 목록을 반환합니다.")
    @GetMapping("/apis")
    public ResponseEntity<CommonResponse<Map<String, String>>> getApiList() {
        
        Map<String, String> apis = new HashMap<>();
        apis.put("플레이어 검색", "GET /api/v1/players/search?nickname={nickname}");
        apis.put("플레이어 상세", "GET /api/v1/players/{userNum}");
        apis.put("매치 기록", "GET /api/v1/players/{userNum}/matches");
        apis.put("랭크 정보", "GET /api/v1/bser/rank/{userNum}/{season}/{teamMode}");
        apis.put("리더보드", "GET /api/v1/bser/rank/leaderboard/{season}/{teamMode}");
        apis.put("매치 히스토리", "GET /api/v1/matches/history");
        apis.put("Swagger UI", "http://localhost:8080/swagger-ui.html");
        
        return ResponseEntity.ok(
            new CommonResponse<>(200, "API 목록 조회 성공", apis)
        );
    }
}
