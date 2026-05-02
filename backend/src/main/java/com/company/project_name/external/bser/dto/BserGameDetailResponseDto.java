package com.company.project_name.external.bser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BserGameDetailResponseDto {

    private int code;
    private String message;
    private List<BserGamePlayer> userGames;

    @Data
    @NoArgsConstructor
    public static class BserGamePlayer {
        private long gameId;
        private long userNum;
        private String nickname;
        private int characterNum;
        private int skinCode;
        private int gameRank;
        private int playerKill;
        private int playerAssistant;
        private int monsterKill;
        private int bestWeapon;
        private int matchingMode;
        private int matchingTeamMode;
        private int playTime;
        private String startDtm;
        private long mmrBefore;
        private long mmrGain;
        private long mmrAfter;
        private List<Integer> equipment;
        private int damageDealt;
        private int damageTaken;
        private int healAmount;
        private int teamNumber;
        private int escapeState;
    }
}
