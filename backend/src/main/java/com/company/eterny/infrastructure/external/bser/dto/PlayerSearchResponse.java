package com.company.eterny.infrastructure.external.bser.dto;

import com.company.eterny.domain.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// ==================== 플레이어 검색 응답 ====================

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSearchResponse {
    private String searchKeyword;
    private List<PlayerDto.Summary> players;
    private Integer totalCount;
}