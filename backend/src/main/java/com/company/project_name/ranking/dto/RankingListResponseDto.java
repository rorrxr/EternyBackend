package com.company.project_name.ranking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RankingListResponseDto {

    private List<RankingEntryDto> rankings;
    private int totalCount;
    private boolean hasMore;
}
