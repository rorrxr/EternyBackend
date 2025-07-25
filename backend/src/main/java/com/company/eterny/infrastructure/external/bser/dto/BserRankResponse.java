package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "BSER 랭크 응답 Wrapper")
public class BserRankResponse {
    
    @Schema(description = "응답 코드")
    @JsonProperty("code")
    private Integer code;

    @Schema(description = "응답 메시지")
    @JsonProperty("message")
    private String message;

    @Schema(description = "유저 랭크 정보")
    @JsonProperty("userRank")
    private BserRankDto userRank;
    
    // 다른 필드명으로 응답이 올 수 있는 경우를 대비
    @JsonProperty("rank")
    private BserRankDto rank;
    
    @JsonProperty("userStats")
    private BserRankDto userStats;
    
    /**
     * 랭크 정보 반환 (다양한 필드명 지원)
     */
    public BserRankDto getUserRank() {
        if (userRank != null) {
            return userRank;
        }
        if (rank != null) {
            return rank;
        }
        if (userStats != null) {
            return userStats;
        }
        return null;
    }
}
