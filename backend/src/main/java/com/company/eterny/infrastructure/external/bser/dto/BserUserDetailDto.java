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
@Schema(description = "BSER 유저 상세 통계")
public class BserUserDetailDto {

    @Schema(description = "유저 고유 번호")
    @JsonProperty("userNum")
    private Long userNum;

    @Schema(description = "닉네임")
    @JsonProperty("nickname")
    private String nickname;

    @Schema(description = "시즌 ID")
    @JsonProperty("seasonId")
    private Integer seasonId;

    @Schema(description = "매칭 모드 (2: 일반, 3: 랭크)")
    @JsonProperty("matchingMode")
    private Integer matchingMode;

    @Schema(description = "팀 매칭 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)")
    @JsonProperty("matchingTeamMode")
    private Integer matchingTeamMode;

    @Schema(description = "MMR")
    @JsonProperty("mmr")
    private Integer mmr;

    @Schema(description = "랭킹")
    @JsonProperty("rank")
    private Integer rank;

    @Schema(description = "랭킹 풀 크기")
    @JsonProperty("rankSize")
    private Integer rankSize;

    @Schema(description = "총 게임 수")
    @JsonProperty("totalGames")
    private Integer totalGames;

    @Schema(description = "총 승리 수")
    @JsonProperty("totalWins")
    private Integer totalWins;

    @Schema(description = "총 팀 킬")
    @JsonProperty("totalTeamKills")
    private Integer totalTeamKills;

    @Schema(description = "랭크 퍼센트")
    @JsonProperty("rankPercent")
    private Double rankPercent;

    @Schema(description = "평균 순위")
    @JsonProperty("averageRank")
    private Double averageRank;

    @Schema(description = "평균 킬")
    @JsonProperty("averageKills")
    private Double averageKills;

    @Schema(description = "평균 어시스트")
    @JsonProperty("averageAssistants")
    private Double averageAssistants;

    @Schema(description = "평균 사냥")
    @JsonProperty("averageHunts")
    private Double averageHunts;

    @Schema(description = "Top 1 달성 횟수")
    @JsonProperty("top1")
    private Integer top1;

    @Schema(description = "Top 2 달성 횟수")
    @JsonProperty("top2")
    private Integer top2;

    @Schema(description = "Top 3 달성 횟수")
    @JsonProperty("top3")
    private Integer top3;

    @Schema(description = "Top 5 달성 횟수")
    @JsonProperty("top5")
    private Integer top5;

    @Schema(description = "Top 6 달성 횟수")
    @JsonProperty("top6")
    private Integer top6;

    @Schema(description = "Top 7 달성 횟수")
    @JsonProperty("top7")
    private Integer top7;

    @Schema(description = "Top 8 달성 횟수")
    @JsonProperty("top8")
    private Integer top8;

    @Schema(description = "승률")
    @JsonProperty("winRate")
    private Double winRate;

    @Schema(description = "계정 레벨")
    @JsonProperty("accountLevel")
    private Integer accountLevel;

    @Schema(description = "캐릭터별 통계")
    @JsonProperty("characterStats")
    private Object characterStats;
}
