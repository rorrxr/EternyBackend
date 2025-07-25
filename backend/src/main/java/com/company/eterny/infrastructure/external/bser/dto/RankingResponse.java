package com.company.eterny.infrastructure.external.bser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingResponse {
    private List<RankingPlayerDto> rankings;
    private Integer teamMode;
    private Integer seasonId;
    private Boolean hasNext;
    private Integer nextCursor;
}