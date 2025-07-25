package com.company.eterny.infrastructure.external.controller;

import com.company.eterny.domain.match.service.MatchService;
import com.company.eterny.global.dto.CommonResponse;
import com.company.eterny.global.service.CacheManagementService;
import com.company.eterny.infrastructure.bser.service.BserService;
import com.company.eterny.infrastructure.external.bser.dto.BserGameDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 외부 API 연동 컨트롤러
 */
@Tag(name = "External API", description = "외부 API 직접 연동 및 시스템 관리")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/external")
@CrossOrigin(origins = "http://localhost:3000")
public class ExternalController {

    private final MatchService matchService;
    private final BserService bserExternalService;
    private final CacheManagementService cacheManagementService;

    /**
     * 특정 매치를 외부 API에서 조회
     * @param matchId 매치 ID
     * @param userNum 조회하는 유저 번호 (권한 확인용)
     * @return 매치 상세 정보
     */
    @Operation(
        summary = "외부 API 매치 상세 조회", 
        description = "BSER API에서 특정 매치의 상세 정보를 직접 조회합니다."
    )
    @GetMapping("/matches/{matchId}")
    public ResponseEntity<CommonResponse<BserGameDetailDto>> getExternalMatch(
            @Parameter(description = "매치 ID", required = true, example = "123456789")
            @PathVariable Long matchId,
            @Parameter(description = "조회하는 유저 번호", required = true, example = "123456")
            @RequestParam Long userNum) {
        
        log.info("외부 API 매치 상세 조회 요청 - matchId: {}, userNum: {}", matchId, userNum);
        
        try {
            // TODO: MVP에서는 임시로 null 반환
            BserGameDetailDto matchDetail = null;
            
            if (matchDetail != null) {
                return ResponseEntity.ok(
                    new CommonResponse<>(200, "매치 상세 정보 조회 성공", matchDetail)
                );
            } else {
                return ResponseEntity.ok(
                    new CommonResponse<>(404, "매치 정보를 찾을 수 없습니다.", null)
                );
            }
            
        } catch (Exception e) {
            log.error("외부 API 매치 조회 실패 - matchId: {}, userNum: {}, error: {}", 
                    matchId, userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "매치 조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 외부 API 상태 확인
     * @return API 상태 정보
     */
    @Operation(
        summary = "외부 API 상태 확인", 
        description = "BSER 외부 API의 연결 상태를 확인합니다."
    )
    @GetMapping("/health")
    public ResponseEntity<CommonResponse<String>> checkExternalApiHealth() {
        
        log.info("외부 API 상태 확인 요청");
        
        try {
            // TODO: MVP에서는 임시로 true 반환
            boolean isHealthy = true;
            
            if (isHealthy) {
                return ResponseEntity.ok(
                    new CommonResponse<>(200, "외부 API 연결 정상", "BSER API 연결 상태: 정상")
                );
            } else {
                return ResponseEntity.ok(
                    new CommonResponse<>(503, "외부 API 연결 불안정", "BSER API 연결 상태: 불안정")
                );
            }
            
        } catch (Exception e) {
            log.error("외부 API 상태 확인 실패 - error: {}", e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "외부 API 연결 실패", "BSER API 연결 상태: 오류 - " + e.getMessage())
            );
        }
    }

    /**
     * API 캐시 갱신
     * @param cacheKey 갱신할 캐시 키 (선택적)
     * @return 캐시 갱신 결과
     */
    @Operation(
        summary = "API 캐시 갱신", 
        description = "외부 API 응답 캐시를 갱신합니다."
    )
    @PostMapping("/cache/refresh")
    public ResponseEntity<CommonResponse<String>> refreshCache(
            @Parameter(description = "갱신할 캐시 키 (전체 갱신 시 생략)")
            @RequestParam(required = false) String cacheKey) {
        
        log.info("API 캐시 갱신 요청 - cacheKey: {}", cacheKey);
        
        try {
            String message;
            boolean success;
            
            if (cacheKey != null && !cacheKey.trim().isEmpty()) {
                success = cacheManagementService.evictCache(cacheKey.trim());
                message = success 
                    ? String.format("캐시 키 '%s' 갱신 완료", cacheKey)
                    : String.format("캐시 키 '%s' 갱신 실패", cacheKey);
            } else {
                int evictedCount = cacheManagementService.evictAllCaches();
                success = evictedCount > 0;
                message = String.format("전체 캐시 갱신 완료 - %d개 캐시 갱신", evictedCount);
            }
            
            return ResponseEntity.ok(
                new CommonResponse<>(success ? 200 : 500, 
                    success ? "캐시 갱신 성공" : "캐시 갱신 실패", message)
            );
            
        } catch (Exception e) {
            log.error("캐시 갱신 실패 - cacheKey: {}, error: {}", cacheKey, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "캐시 갱신 실패", "캐시 갱신 중 오류가 발생했습니다: " + e.getMessage())
            );
        }
    }

    /**
     * 캐시 상태 조회
     * @return 캐시 상태 정보
     */
    @Operation(
        summary = "캐시 상태 조회", 
        description = "현재 캐시 상태를 조회합니다."
    )
    @GetMapping("/cache/status")
    public ResponseEntity<CommonResponse<Map<String, Object>>> getCacheStatus() {
        
        log.info("캐시 상태 조회 요청");
        
        try {
            Map<String, Object> cacheStatus = cacheManagementService.getCacheStatus();
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "캐시 상태 조회 성공", cacheStatus)
            );
            
        } catch (Exception e) {
            log.error("캐시 상태 조회 실패 - error: {}", e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "캐시 상태 조회 실패", null)
            );
        }
    }

    /**
     * 플레이어별 캐시 갱신
     * @param userNum 유저 번호 (선택적)
     * @return 캐시 갱신 결과
     */
    @Operation(
        summary = "플레이어 캐시 갱신", 
        description = "특정 플레이어 또는 전체 플레이어 관련 캐시를 갱신합니다."
    )
    @PostMapping("/cache/refresh/player")
    public ResponseEntity<CommonResponse<String>> refreshPlayerCache(
            @Parameter(description = "유저 번호 (전체 갱신 시 생략)")
            @RequestParam(required = false) Long userNum) {
        
        log.info("플레이어 캐시 갱신 요청 - userNum: {}", userNum);
        
        try {
            int evictedCount = cacheManagementService.evictPlayerCaches(userNum);
            
            String message = (userNum != null) 
                ? String.format("유저 %d 관련 캐시 %d개 갱신 완료", userNum, evictedCount)
                : String.format("전체 플레이어 캐시 %d개 갱신 완료", evictedCount);
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "플레이어 캐시 갱신 성공", message)
            );
            
        } catch (Exception e) {
            log.error("플레이어 캐시 갱신 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "플레이어 캐시 갱신 실패", 
                    "플레이어 캐시 갱신 중 오류가 발생했습니다: " + e.getMessage())
            );
        }
    }
}
