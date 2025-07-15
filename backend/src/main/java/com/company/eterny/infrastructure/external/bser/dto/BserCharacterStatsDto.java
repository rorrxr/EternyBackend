package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "캐릭터 통계")
public class BserCharacterStatsDto {
//    @Schema(description = "시즌 ID")
//    private Integer seasonId;
//
//    @Schema(description = "캐릭터 코드")
//    private Integer characterCode;
//
//    @Schema(description = "총 게임 수")
//    private Integer totalGames;
//
//    @Schema(description = "사용 횟수")
//    private Integer usages;
//
//    @Schema(description = "최대 킬")
//    private Integer maxKillings;
//
//    @Schema(description = "3등 이상")
//    private Integer top3;
//
//    @Schema(description = "승리")
//    private Integer wins;
//
//    @Schema(description = "평균 순위")
//    private Double averageRank;
//
//    @Schema(description = "평균 킬")
//    private Double averageKills;
//
//    @Schema(description = "평균 어시스트")
//    private Double averageAssistants;
//
//    @Schema(description = "평균 사냥")
//    private Double averageHunts;
//
//    @Schema(description = "1등 비율")
//    private Double top1Rate;
//
//    @Schema(description = "3등 이상 비율")
//    private Double top3Rate;

    @JsonProperty("seasonId")
    private Integer seasonId;

    @JsonProperty("userNum")
    private Long userNum;

    @JsonProperty("characterNum")
    private Integer characterNum;

    @JsonProperty("totalGames")
    private Integer totalGames;

    @JsonProperty("usages")
    private Integer usages;

    @JsonProperty("maxKillings")
    private Integer maxKillings;

    @JsonProperty("top3")
    private Integer top3;

    @JsonProperty("wins")
    private Integer wins;

    @JsonProperty("top3Rate")
    private Double top3Rate;

    @JsonProperty("averageRank")
    private Double averageRank;

}
