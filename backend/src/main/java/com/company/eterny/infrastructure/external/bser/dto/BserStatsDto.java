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
@Schema(description = "BSER 유저 통계 정보")
public class BserStatsDto {
    
    @Schema(description = "유저 번호")
    @JsonProperty("userNum")
    private Long userNum;

    @Schema(description = "시즌 ID")
    @JsonProperty("seasonId")
    private Integer seasonId;

    @Schema(description = "매칭 모드")
    @JsonProperty("matchingMode")
    private Integer matchingMode;

    @Schema(description = "팀 매칭 모드")
    @JsonProperty("matchingTeamMode")
    private Integer matchingTeamMode;

    @Schema(description = "총 게임 수")
    @JsonProperty("totalGames")
    private Integer totalGames;

    @Schema(description = "총 승리 수")
    @JsonProperty("totalWins")
    private Integer totalWins;

    @Schema(description = "총 패배 수")
    @JsonProperty("totalLosses")
    private Integer totalLosses;

    @Schema(description = "승률")
    @JsonProperty("winRate")
    private Double winRate;

    @Schema(description = "평균 순위")
    @JsonProperty("averageRank")
    private Double averageRank;

    @Schema(description = "평균 킬")
    @JsonProperty("averageKills")
    private Double averageKills;

    @Schema(description = "평균 데스")
    @JsonProperty("averageDeaths")
    private Double averageDeaths;

    @Schema(description = "평균 어시스트")
    @JsonProperty("averageAssists")
    private Double averageAssists;

    // 편의 메서드들
    public Integer getWins() {
        return totalWins;
    }

    public Integer getLosses() {
        return totalLosses;
    }
} 