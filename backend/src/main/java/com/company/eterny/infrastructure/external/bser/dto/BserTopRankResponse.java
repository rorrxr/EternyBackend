package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "탑 랭킹 응답")
public class BserTopRankResponse {
    @Schema(description = "응답 코드")
    private Integer code;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "탑 랭킹 리스트")
    private List<BserTopRankDto> topRanks;
}
