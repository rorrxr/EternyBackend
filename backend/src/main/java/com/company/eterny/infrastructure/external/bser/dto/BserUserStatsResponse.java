package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "캐릭터 통계 응답")
public class BserUserStatsResponse {
    private int code;
    private String message;
    private List<BserUserDetailDto> userStats;
}
