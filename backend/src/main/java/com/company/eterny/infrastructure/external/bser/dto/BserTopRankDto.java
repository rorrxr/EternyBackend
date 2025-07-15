package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "탑 랭킹 정보")
public class BserTopRankDto {
//    @Schema(description = "순위")
//    private Integer rank;
//
//    @Schema(description = "유저 번호")
//    private Long userNum;
//
//    @Schema(description = "닉네임")
//    private String nickname;
//
//    @Schema(description = "MMR")
//    private Integer mmr;
//
//    @Schema(description = "시즌 ID")
//    private Integer seasonId;
//
//    @Schema(description = "팀 매칭 모드")
//    private Integer matchingTeamMode;


//    @JsonProperty("userNum")
//    private Long userNum;
//
//    @JsonProperty("nickname")
//    private String nickname;
//
//    @JsonProperty("rank")
//    private Integer rank;
//
//    @JsonProperty("mmr")
//    private Integer mmr;
//
//    @JsonProperty("seasonId")
//    private Integer seasonId;
//
//    @JsonProperty("matchingTeamMode")
//    private Integer matchingTeamMode;

    private Long userNum;
    private String nickname;
    private int rank;
    private int mmr;
    private int totalGames;
    private int totalWins;

}