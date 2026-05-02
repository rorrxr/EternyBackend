package com.company.project_name.external.bser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BserLeaderboardResponseDto {

    private int code;
    private String message;
    private List<BserRankEntry> topRanks;

    @Data
    @NoArgsConstructor
    public static class BserRankEntry {
        private int rank;
        private long userNum;
        private String nickname;
        private int mmr;
        private String tier;
    }
}
