package com.company.project_name.player.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayerDetailResponseDto {

    private long userNum;
    private String nickname;
    private String tier;
    private int rank;
    private int mmr;
    private int totalGames;
    private int wins;
    private double winRate;
    private List<MatchDto> recentMatches;
    private boolean isFromExternalApi;
    private String updatedAt;
}
