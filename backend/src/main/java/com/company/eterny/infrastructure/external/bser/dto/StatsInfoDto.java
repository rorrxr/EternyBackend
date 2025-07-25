package com.company.eterny.infrastructure.external.bser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsInfoDto {
    private Integer totalGames;
    private Integer wins;
    private Integer losses;
    private Double winRate;
    private Double averageRank;
    private Double averageKills;
    private Double averageDeaths;
    private Double averageAssists;

    // 편의 메서드
    public Double getKda() {
        if (averageDeaths == null || averageDeaths == 0) {
            return (averageKills != null ? averageKills : 0) + (averageAssists != null ? averageAssists : 0);
        }
        return Math.round(((averageKills + averageAssists) / averageDeaths) * 100.0) / 100.0;
    }
}