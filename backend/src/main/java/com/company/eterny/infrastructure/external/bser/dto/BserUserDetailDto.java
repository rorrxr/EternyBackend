package com.company.eterny.infrastructure.external.bser.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "BSER 유저 상세 통계 V2")
public class BserUserDetailDto {

    @JsonProperty("userNum")
    private Long userNum;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("seasonId")
    private Integer seasonId;

    @JsonProperty("matchingMode")
    private Integer matchingMode;

    @JsonProperty("matchingTeamMode")
    private Integer matchingTeamMode;

    @JsonProperty("mmr")
    private Integer mmr;

    @JsonProperty("rank")
    private Integer rank;

    @JsonProperty("rankSize")
    private Integer rankSize;

    @JsonProperty("totalGames")
    private Integer totalGames;

    @JsonProperty("totalWins")
    private Integer totalWins;

    @JsonProperty("totalTeamKills")
    private Integer totalTeamKills;

    @JsonProperty("rankPercent")
    private Double rankPercent;

    @JsonProperty("averageRank")
    private Double averageRank;

    @JsonProperty("averageKills")
    private Double averageKills;

    @JsonProperty("averageAssistants")
    private Double averageAssistants;

    @JsonProperty("averageHunts")
    private Double averageHunts;

    @JsonProperty("top1")
    private Integer top1;

    @JsonProperty("top2")
    private Integer top2;

    @JsonProperty("top3")
    private Integer top3;

    @JsonProperty("top5")
    private Integer top5;

    @JsonProperty("top6")
    private Integer top6;

    @JsonProperty("top7")
    private Integer top7;

    @JsonProperty("top8")
    private Integer top8;
    //    @Schema(description = "유저 고유 번호")
//    private Long userNum;
//
//    @Schema(description = "닉네임")
//    private String nickname;
//
//    @Schema(description = "시즌 ID")
//    private Integer seasonId;
//
//    @Schema(description = "매칭 모드 (2: 일반, 3: 랭크)")
//    private Integer matchingMode;
//
//    @Schema(description = "팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드, 8: 유니온)")
//    private Integer matchingTeamMode;
//
//    @Schema(description = "MMR")
//    private Integer mmr;
//
//    @Schema(description = "랭킹")
//    private Integer rank;
//
//    @Schema(description = "랭킹 규모")
//    private Integer rankSize;
//
//    @Schema(description = "총 게임 수")
//    private Integer totalGames;
//
//    @Schema(description = "총 승리 수")
//    private Integer totalWins;
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
//    @Schema(description = "Top1 비율")
//    private Double top1;
//
//    @Schema(description = "Top2 비율")
//    private Double top2;
//
//    @Schema(description = "Top3 비율")
//    private Double top3;
//
//    @Schema(description = "캐릭터별 통계")
//    private List<BserCharacterStatsDto> characterStats;
}