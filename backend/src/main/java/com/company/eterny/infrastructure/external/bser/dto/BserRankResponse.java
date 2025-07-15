package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "BSER 랭크 응답 Wrapper")
public class BserRankResponse {
    @Schema(description = "응답 코드")
    private Integer code;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "유저 랭크 정보")
    private BserRankDto userRank;
}