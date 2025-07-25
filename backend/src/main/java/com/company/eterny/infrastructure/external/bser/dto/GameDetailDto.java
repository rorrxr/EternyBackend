package com.company.eterny.infrastructure.external.bser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDetailDto {
    private Long gameId;
    private Integer characterNum;
    private Integer gameRank;
    private Integer gameMode;
    private Integer teamMode;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer playTime;
    private LocalDateTime startDate;

    // 추가 상세 정보
    private Integer weaponType;
    private Integer damageToPlayer;
    private Integer damageFromPlayer;
    private Integer healAmount;

    // 편의 메서드
    public String getGameResult() {
        if (gameRank == null) return "Unknown";
        return gameRank <= 3 ? "승리" : "패배";
    }

    public String getKdaString() {
        return String.format("%d/%d/%d",
                kills != null ? kills : 0,
                deaths != null ? deaths : 0,
                assists != null ? assists : 0);
    }

    public Double getKda() {
        if (deaths == null || deaths == 0) {
            return (double) ((kills != null ? kills : 0) + (assists != null ? assists : 0));
        }
        return Math.round(((double)(kills + assists) / deaths) * 100.0) / 100.0;
    }

    public String getPlayTimeFormatted() {
        if (playTime == null) return "00:00";
        int minutes = playTime / 60;
        int seconds = playTime % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public String getGameModeString() {
        if (gameMode == null) return "Unknown";
        return switch (gameMode) {
            case 0 -> "일반";
            case 1 -> "랭크";
            case 2 -> "코발트";
            default -> "기타";
        };
    }

    public String getTeamModeString() {
        if (teamMode == null) return "Unknown";
        return switch (teamMode) {
            case 1 -> "솔로";
            case 2 -> "듀오";
            case 3 -> "스쿼드";
            default -> "기타";
        };
    }
}