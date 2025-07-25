package com.company.eterny.infrastructure.external.bser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRanksDto {
    private RankInfoDto solo;
    private RankInfoDto duo;
    private RankInfoDto squad;
}