package com.company.project_name.ranking.dto;

import com.company.project_name.external.bser.dto.BserLeaderboardResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankingEntryDto {

    private int rank;
    private long userNum;
    private String nickname;
    private int mmr;
    private String tier;

    public static RankingEntryDto fromBser(BserLeaderboardResponseDto.BserRankEntry entry) {
        return RankingEntryDto.builder()
                .rank(entry.getRank())
                .userNum(entry.getUserNum())
                .nickname(entry.getNickname())
                .mmr(entry.getMmr())
                .tier(entry.getTier())
                .build();
    }
}
