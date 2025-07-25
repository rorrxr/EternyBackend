package com.company.eterny.infrastructure.external.bser.dto;

import com.company.eterny.domain.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// ==================== 플레이어 상세 응답 ====================

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDetailResponse {
    private PlayerDto.Response player;
    private PlayerRanksDto ranks;
    private StatsInfoDto stats;
    private List<GameSummaryDto> recentGames;
}