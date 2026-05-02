package com.company.project_name.player.dto;

import com.company.project_name.external.bser.dto.BserRankResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerRankResponseDto {

    private String tier;
    private int rank;
    private int mmr;

    public static PlayerRankResponseDto fromBser(BserRankResponseDto.BserRankInfo info) {
        return PlayerRankResponseDto.builder()
                .tier(info.getTier())
                .rank(info.getRank())
                .mmr(info.getMmr())
                .build();
    }

    public static PlayerRankResponseDto unranked() {
        return PlayerRankResponseDto.builder()
                .tier("UNRANKED")
                .rank(0)
                .mmr(0)
                .build();
    }
}
