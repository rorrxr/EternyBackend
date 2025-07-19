package com.company.eterny.domain.rank.dto;

import com.company.eterny.infrastructure.external.bser.dto.BserRankDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 랭크 예측 결과 DTO
 */
@Data
@Builder
@Schema(description = "랭크 예측 결과")
public class RankPredictionDto {
    
    @Schema(description = "현재 랭크 정보")
    private BserRankDto currentRank;
    
    @Schema(description = "예측된 티어", example = "PLATINUM")
    private String predictedTier;
    
    @Schema(description = "예측된 단계", example = "3")
    private Integer predictedDivision;
    
    @Schema(description = "추가로 필요한 승리 수", example = "15")
    private Integer additionalWinsNeeded;
    
    @Schema(description = "예측 메시지")
    private String message;
    
    @Schema(description = "예측 정확도 (%)", example = "75")
    private Double accuracy;
}
