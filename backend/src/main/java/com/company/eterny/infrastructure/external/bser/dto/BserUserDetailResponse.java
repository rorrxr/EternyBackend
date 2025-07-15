package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "BSER 유저 상세 통계 응답")
public class BserUserDetailResponse {
    @Schema(description = "응답 코드")
    private Integer code;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "유저 상세 통계 목록 (매칭모드별)")
    private List<BserUserDetailDto> userStats;
}