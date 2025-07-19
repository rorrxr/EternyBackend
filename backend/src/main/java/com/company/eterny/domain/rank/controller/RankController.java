package com.company.eterny.domain.rank.controller;

import com.company.eterny.domain.rank.dto.RankPredictionDto;
import com.company.eterny.domain.rank.service.RankService;
import com.company.eterny.global.dto.CommonResponse;
import com.company.eterny.infrastructure.external.bser.dto.BserRankDto;
import com.company.eterny.infrastructure.external.bser.dto.BserTopRankDto;
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
 * 랭크 관련 API 컨트롤러
 */
@Tag(name = "Rank API", description = "랭크 정보 및 리더보드 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bser/rank")
@CrossOrigin(origins = "http://localhost:3000")
public class RankController {

    private final RankService rankService;

    /**
     * 플레이어 랭크 정보 조회
     * @param userNum 유저 번호
     * @param season 시즌
     * @param teamMode 팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)
     * @return 랭크 정보
     */
    @Operation(
        summary = "플레이어 랭크 정보 조회", 
        description = "특정 플레이어의 시즌 및 모드별 랭크 정보를 조회합니다."
    )
    @GetMapping("/{userNum}/{season}/{teamMode}")
    public ResponseEntity<CommonResponse<BserRankDto>> getPlayerRank(
            @Parameter(description = "유저 번호", required = true, example = "123456")
            @PathVariable Long userNum,
            @Parameter(description = "시즌", required = true, example = "31")
            @PathVariable Integer season,
            @Parameter(description = "팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)", required = true, example = "1")
            @PathVariable Integer teamMode) {
        
        log.info("플레이어 랭크 정보 조회 요청 - userNum: {}, season: {}, teamMode: {}", userNum, season, teamMode);
        
        try {
            BserRankDto rankInfo = rankService.getPlayerRank(userNum, season, teamMode);
            
            if (rankInfo != null) {
                return ResponseEntity.ok(
                    new CommonResponse<>(200, "랭크 정보 조회 성공", rankInfo)
                );
            } else {
                return ResponseEntity.ok(
                    new CommonResponse<>(404, "랭크 정보를 찾을 수 없습니다.", null)
                );
            }
            
        } catch (Exception e) {
            log.error("랭크 정보 조회 실패 - userNum: {}, season: {}, teamMode: {}, error: {}", 
                    userNum, season, teamMode, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "랭크 정보 조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 리더보드 조회
     * @param season 시즌
     * @param teamMode 팀 모드
     * @param page 페이지 번호 (0부터 시작)
     * @param limit 페이지 크기
     * @return 페이징된 리더보드
     */
    @Operation(
        summary = "리더보드 조회", 
        description = "시즌 및 모드별 상위 랭킹 리더보드를 조회합니다."
    )
    @GetMapping("/leaderboard/{season}/{teamMode}")
    public ResponseEntity<CommonResponse<Page<BserTopRankDto>>> getLeaderboard(
            @Parameter(description = "시즌", required = true, example = "31")
            @PathVariable Integer season,
            @Parameter(description = "팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)", required = true, example = "1")
            @PathVariable Integer teamMode,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "50")
            @RequestParam(defaultValue = "50") int limit) {
        
        log.info("리더보드 조회 요청 - season: {}, teamMode: {}, page: {}, limit: {}", 
                season, teamMode, page, limit);
        
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<BserTopRankDto> leaderboard = rankService.getLeaderboard(season, teamMode, pageable);
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "리더보드 조회 성공", leaderboard)
            );
            
        } catch (Exception e) {
            log.error("리더보드 조회 실패 - season: {}, teamMode: {}, error: {}", 
                    season, teamMode, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "리더보드 조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 승리 수 기반 랭크 예측
     * @param userNum 유저 번호
     * @param season 시즌
     * @param teamMode 팀 모드
     * @param targetWins 목표 승리 수
     * @return 예측된 랭크 정보
     */
    @Operation(
        summary = "랭크 예측", 
        description = "현재 랭크를 기반으로 목표 승리 수 달성 시의 예상 랭크를 예측합니다."
    )
    @GetMapping("/{userNum}/{season}/{teamMode}/predict")
    public ResponseEntity<CommonResponse<RankPredictionDto>> predictRank(
            @Parameter(description = "유저 번호", required = true, example = "123456")
            @PathVariable Long userNum,
            @Parameter(description = "시즌", required = true, example = "31")
            @PathVariable Integer season,
            @Parameter(description = "팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)", required = true, example = "1")
            @PathVariable Integer teamMode,
            @Parameter(description = "목표 승리 수", required = true, example = "100")
            @RequestParam("targetWins") Integer targetWins) {
        
        log.info("랭크 예측 요청 - userNum: {}, season: {}, teamMode: {}, targetWins: {}", 
                userNum, season, teamMode, targetWins);
        
        try {
            if (targetWins <= 0) {
                return ResponseEntity.ok(
                    new CommonResponse<>(400, "목표 승리 수는 0보다 커야 합니다.", null)
                );
            }
            
            RankPredictionDto prediction = rankService.predictRank(userNum, season, teamMode, targetWins);
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "랭크 예측 성공", prediction)
            );
            
        } catch (Exception e) {
            log.error("랭크 예측 실패 - userNum: {}, targetWins: {}, error: {}", 
                    userNum, targetWins, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "랭크 예측 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }
}
