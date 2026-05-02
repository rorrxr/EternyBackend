package com.company.project_name.external.bser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BserRankResponseDto {

    private int code;
    private String message;
    private BserRankInfo rank;

    @Data
    @NoArgsConstructor
    public static class BserRankInfo {
        private long userNum;
        private int seasonId;
        private int matchingTeamMode;
        private int mmr;
        private int rank;
        private String tier;
    }
}
