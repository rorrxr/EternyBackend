package com.company.eterny.domain.match.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 매치 통계 정보 DTO
 */
@Data
@Builder
@Schema(description = "매치 통계 정보")
public class MatchStatsDto {
    
    @Schema(description = "총 게임 수", example = "150")
    private Integer totalMatches;
    
    @Schema(description = "승리 수", example = "45")
    private Integer wins;
    
    @Schema(description = "승률 (%)", example = "30.0")
    private Double winRate;
    
    @Schema(description = "평균 순위", example = "5.2")
    private Double averageRank;
    
    @Schema(description = "가장 많이 플레이한 캐릭터 ID", example = "1")
    private Integer mostPlayedCharacter;
    
    @Schema(description = "최고 순위", example = "1")
    private Integer bestRank;
    
    @Schema(description = "평균 킬 수", example = "2.5")
    private Double averageKills;
    
    @Schema(description = "평균 생존 시간 (초)", example = "420")
    private Integer averageSurvivalTime;
    
    @Schema(description = "Top 3 달성 횟수", example = "75")
    private Integer top3Count;
    
    @Schema(description = "Top 3 달성률 (%)", example = "50.0")
    private Double top3Rate;
}
