package com.company.project_name.external.bser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BserUserStatsResponseDto {

    private int code;
    private String message;
    private List<BserSeasonStat> userStats;

    @Data
    @NoArgsConstructor
    public static class BserSeasonStat {
        private long userNum;
        private int seasonId;
        private int matchingTeamMode;
        private int mmr;
        private int rank;
        private String tier;
        private int totalGames;
        private int wins;
        private double top3;
        private double averageRank;
        private double averageKills;
        private double averageAssistants;
        private double averageHunts;
    }
}
