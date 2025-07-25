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
public class GameSummaryDto {
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

    public String getPlayTimeFormatted() {
        if (playTime == null) return "00:00";
        int minutes = playTime / 60;
        int seconds = playTime % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}