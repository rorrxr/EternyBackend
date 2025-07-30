package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "무기 마스터 데이터 응답")
public class BserWeaponResponse {
    @Schema(description = "응답 코드")
    private Integer code;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "무기 데이터 리스트")
    private List<BserWeaponDto> data;
} 