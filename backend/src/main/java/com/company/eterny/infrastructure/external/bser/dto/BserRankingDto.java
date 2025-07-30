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
@Schema(description = "BSER 랭킹 정보")
public class BserRankingDto {
    
    @Schema(description = "유저 번호", example = "123456")
    @JsonProperty("userNum")
    private Long userNum;
    
    @Schema(description = "닉네임", example = "Hide on bush")
    @JsonProperty("nickname")
    private String nickname;
    
    @Schema(description = "순위", example = "1")
    @JsonProperty("rank")
    private Integer rank;
    
    @Schema(description = "MMR", example = "5500")
    @JsonProperty("mmr")
    private Integer mmr;
    
    @Schema(description = "총 게임 수", example = "150")
    @JsonProperty("totalGames")
    private Integer totalGames;
    
    @Schema(description = "총 승리 수", example = "45")
    @JsonProperty("totalWins")
    private Integer totalWins;

    @Schema(description = "승률")
    @JsonProperty("winRate")
    private Double winRate;

    @Schema(description = "평균 순위")
    @JsonProperty("averageRank")
    private Double averageRank;
} 