package com.company.project_name.player.dto;

import com.company.project_name.external.bser.dto.BserUserStatsResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PlayerStatsResponseDto {

    private int totalGames;
    private int wins;
    private double winRate;
    private double averageRank;
    private double averageKills;
    private List<GameModeStatsDto> gameModeStats;

    @Data
    @Builder
    public static class GameModeStatsDto {
        private int matchingTeamMode;
        private String modeName;
        private int totalGames;
        private int wins;
        private double winRate;
        private double averageRank;
        private double averageKills;
    }

    public static PlayerStatsResponseDto fromBser(BserUserStatsResponseDto bserStats) {
        if (bserStats == null || bserStats.getUserStats() == null) {
            return PlayerStatsResponseDto.builder()
                    .totalGames(0).wins(0).winRate(0).averageRank(0).averageKills(0)
                    .gameModeStats(List.of()).build();
        }

        List<BserUserStatsResponseDto.BserSeasonStat> stats = bserStats.getUserStats();

        int totalGames = stats.stream().mapToInt(BserUserStatsResponseDto.BserSeasonStat::getTotalGames).sum();
        int totalWins  = stats.stream().mapToInt(BserUserStatsResponseDto.BserSeasonStat::getWins).sum();
        double avgRank = stats.stream().mapToDouble(BserUserStatsResponseDto.BserSeasonStat::getAverageRank).average().orElse(0);
        double avgKills = stats.stream().mapToDouble(BserUserStatsResponseDto.BserSeasonStat::getAverageKills).average().orElse(0);

        List<GameModeStatsDto> modeStats = stats.stream()
                .map(s -> GameModeStatsDto.builder()
                        .matchingTeamMode(s.getMatchingTeamMode())
                        .modeName(resolveModeName(s.getMatchingTeamMode()))
                        .totalGames(s.getTotalGames())
                        .wins(s.getWins())
                        .winRate(s.getTotalGames() > 0 ? (double) s.getWins() / s.getTotalGames() * 100 : 0)
                        .averageRank(s.getAverageRank())
                        .averageKills(s.getAverageKills())
                        .build())
                .collect(Collectors.toList());

        return PlayerStatsResponseDto.builder()
                .totalGames(totalGames)
                .wins(totalWins)
                .winRate(totalGames > 0 ? (double) totalWins / totalGames * 100 : 0)
                .averageRank(avgRank)
                .averageKills(avgKills)
                .gameModeStats(modeStats)
                .build();
    }

    private static String resolveModeName(int teamMode) {
        return switch (teamMode) {
            case 1 -> "솔로";
            case 2 -> "듀오";
            case 3 -> "스쿼드";
            default -> "알 수 없음";
        };
    }
}
