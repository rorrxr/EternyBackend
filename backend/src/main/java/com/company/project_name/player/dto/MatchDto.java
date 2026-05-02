package com.company.project_name.player.dto;

import com.company.project_name.external.bser.dto.BserUserGamesResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MatchDto {

    private String matchId;
    private String character;
    private int characterId;
    private int gameRank;
    private String gameMode;
    private String matchingMode;
    private int playTime;
    private String startDtm;
    private String nickname;
    private int kills;
    private int assists;
    private Long mmrBefore;
    private Long mmrAfter;
    private Long mmrGain;
    private List<Integer> equipment;

    public static MatchDto fromBserGame(BserUserGamesResponseDto.BserGame game) {
        return MatchDto.builder()
                .matchId(String.valueOf(game.getGameId()))
                .characterId(game.getCharacterNum())
                .character(String.valueOf(game.getCharacterNum()))
                .gameRank(game.getGameRank())
                .gameMode(resolveGameMode(game.getMatchingTeamMode()))
                .matchingMode(resolveMatchingMode(game.getMatchingMode()))
                .playTime(game.getPlayTime())
                .startDtm(game.getStartDtm())
                .nickname(game.getNickname())
                .kills(game.getPlayerKill())
                .assists(game.getPlayerAssistant())
                .mmrBefore(game.getMmrBefore())
                .mmrAfter(game.getMmrAfter())
                .mmrGain(game.getMmrGain())
                .equipment(game.getEquipment())
                .build();
    }

    private static String resolveGameMode(int teamMode) {
        return switch (teamMode) {
            case 1 -> "솔로";
            case 2 -> "듀오";
            case 3 -> "스쿼드";
            default -> "알 수 없음";
        };
    }

    private static String resolveMatchingMode(int mode) {
        return switch (mode) {
            case 1 -> "일반";
            case 2 -> "랭크";
            case 3 -> "커스텀";
            default -> "알 수 없음";
        };
    }
}
