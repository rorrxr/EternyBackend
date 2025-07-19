package com.company.eterny.domain.match.controller;

import com.company.eterny.domain.match.dto.MatchHistoryFilterDto;
import com.company.eterny.domain.match.dto.MatchStatsDto;
import com.company.eterny.domain.match.service.MatchService;
import com.company.eterny.global.dto.CommonResponse;
import com.company.eterny.infrastructure.external.bser.dto.BserGameDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 매치 관련 API 컨트롤러
 */
@Tag(name = "Match API", description = "매치 기록 및 통계 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/matches")
@CrossOrigin(origins = "http://localhost:3000")
public class MatchController {

    private final MatchService matchService;

    /**
     * 필터 기반 매치 히스토리 조회
     * @param userNum 유저 번호 (필수)
     * @param season 시즌
     * @param gameMode 게임 모드
     * @param characterId 캐릭터 ID
     * @param teamMode 팀 모드
     * @param cursor 커서 (페이징용)
     * @param startDate 시작 날짜
     * @param endDate 종료 날짜
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @return 필터링된 매치 기록
     */
    @Operation(
        summary = "매치 히스토리 조회", 
        description = "다양한 필터 조건을 적용하여 매치 히스토리를 조회합니다."
    )
    @GetMapping("/history")
    public ResponseEntity<CommonResponse<Page<BserGameDto>>> getMatchHistory(
            @Parameter(description = "유저 번호", required = true, example = "123456")
            @RequestParam Long userNum,
            @Parameter(description = "시즌", example = "31")
            @RequestParam(required = false) Integer season,
            @Parameter(description = "게임 모드", example = "normal")
            @RequestParam(required = false) String gameMode,
            @Parameter(description = "캐릭터 ID", example = "1")
            @RequestParam(required = false) Integer characterId,
            @Parameter(description = "팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)", example = "1")
            @RequestParam(required = false) Integer teamMode,
            @Parameter(description = "커서 (페이징용)")
            @RequestParam(required = false) String cursor,
            @Parameter(description = "시작 날짜 (YYYY-MM-DD)", example = "2024-01-01")
            @RequestParam(required = false) String startDate,
            @Parameter(description = "종료 날짜 (YYYY-MM-DD)", example = "2024-12-31")
            @RequestParam(required = false) String endDate,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "20")
            @RequestParam(defaultValue = "20") int size) {
        
        log.info("매치 히스토리 조회 요청 - userNum: {}, season: {}, gameMode: {}, characterId: {}", 
                userNum, season, gameMode, characterId);
        
        try {
            // 필터 DTO 생성
            MatchHistoryFilterDto filter = new MatchHistoryFilterDto();
            filter.setUserNum(userNum);
            filter.setSeason(season);
            filter.setGameMode(gameMode);
            filter.setCharacterId(characterId);
            filter.setTeamMode(teamMode);
            filter.setCursor(cursor);
            filter.setStartDate(startDate);
            filter.setEndDate(endDate);
            
            Pageable pageable = PageRequest.of(page, size);
            Page<BserGameDto> matchHistory = matchService.getMatchHistory(filter, pageable);
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "매치 히스토리 조회 성공", matchHistory)
            );
            
        } catch (Exception e) {
            log.error("매치 히스토리 조회 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "매치 히스토리 조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 매치 통계 조회
     * @param userNum 유저 번호
     * @param season 시즌 (선택적)
     * @return 매치 통계 정보
     */
    @Operation(
        summary = "매치 통계 조회", 
        description = "플레이어의 매치 통계 정보를 조회합니다."
    )
    @GetMapping("/stats")
    public ResponseEntity<CommonResponse<MatchStatsDto>> getMatchStats(
            @Parameter(description = "유저 번호", required = true, example = "123456")
            @RequestParam Long userNum,
            @Parameter(description = "시즌", example = "31")
            @RequestParam(required = false) Integer season) {
        
        log.info("매치 통계 조회 요청 - userNum: {}, season: {}", userNum, season);
        
        try {
            MatchStatsDto stats = matchService.getMatchStats(userNum, season);
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "매치 통계 조회 성공", stats)
            );
            
        } catch (Exception e) {
            log.error("매치 통계 조회 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "매치 통계 조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 플레이어의 최근 매치 목록 (간단 조회)
     * @param userNum 유저 번호
     * @param limit 조회할 개수
     * @return 최근 매치 목록
     */
    @Operation(
        summary = "최근 매치 간단 조회", 
        description = "플레이어의 최근 매치 목록을 간단히 조회합니다."
    )
    @GetMapping("/recent")
    public ResponseEntity<CommonResponse<java.util.List<BserGameDto>>> getRecentMatches(
            @Parameter(description = "유저 번호", required = true, example = "123456")
            @RequestParam Long userNum,
            @Parameter(description = "조회할 개수", example = "10")
            @RequestParam(defaultValue = "10") int limit) {
        
        log.info("최근 매치 조회 요청 - userNum: {}, limit: {}", userNum, limit);
        
        try {
            java.util.List<BserGameDto> matches = matchService.getUserMatches(userNum);
            
            // 제한된 개수만 반환
            java.util.List<BserGameDto> limitedMatches = matches.stream()
                    .limit(limit)
                    .collect(java.util.stream.Collectors.toList());
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "최근 매치 조회 성공", limitedMatches)
            );
            
        } catch (Exception e) {
            log.error("최근 매치 조회 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "최근 매치 조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }
}
