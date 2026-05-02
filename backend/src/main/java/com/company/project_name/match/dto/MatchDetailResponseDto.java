package com.company.project_name.match.dto;

import com.company.project_name.external.bser.dto.BserGameDetailResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class MatchDetailResponseDto {

    private String matchId;
    private int characterId;
    private int gameRank;
    private String gameMode;
    private String matchingMode;
    private int playTime;
    private String startDtm;
    private List<ParticipantDto> participants;

    public static MatchDetailResponseDto fromBser(BserGameDetailResponseDto detail, long gameId) {
        if (detail.getUserGames() == null || detail.getUserGames().isEmpty()) {
            return MatchDetailResponseDto.builder()
                    .matchId(String.valueOf(gameId))
                    .participants(List.of())
                    .build();
        }

        BserGameDetailResponseDto.BserGamePlayer first = detail.getUserGames().get(0);
        List<ParticipantDto> participants = detail.getUserGames().stream()
                .map(ParticipantDto::fromBser)
                .collect(Collectors.toList());

        return MatchDetailResponseDto.builder()
                .matchId(String.valueOf(gameId))
                .characterId(first.getCharacterNum())
                .gameRank(first.getGameRank())
                .gameMode(resolveGameMode(first.getMatchingTeamMode()))
                .matchingMode(resolveMatchingMode(first.getMatchingMode()))
                .playTime(first.getPlayTime())
                .startDtm(first.getStartDtm())
                .participants(participants)
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
