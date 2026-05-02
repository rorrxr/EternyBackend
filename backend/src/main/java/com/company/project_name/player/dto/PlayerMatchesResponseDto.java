package com.company.project_name.player.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayerMatchesResponseDto {

    private List<MatchDto> matches;
    private int totalCount;
    private boolean hasMore;
}
