package com.company.eterny.infrastructure.external.bser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingPlayerDto {
    private Long userNum;
    private String nickname;
    private Integer rank;
    private String tier;
    private Integer division;
    private Integer lp;
    private Integer totalGames;
    private Integer wins;
    private Double winRate;

    // 편의 메서드
    public String getFullRank() {
        if (tier == null) return "언랭크";
        if (division == null || division == 0) return tier;
        return tier + " " + division;
    }
}