package com.company.eterny.infrastructure.external.bser.dto;

import com.company.eterny.domain.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// ==================== 게임 전적 응답 ====================

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerGamesResponse {
    private PlayerDto.Summary player;
    private List<GameDetailDto> games;
    private Boolean hasNext;
    private Long nextCursor;
}