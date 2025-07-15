package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "시즌 정보")
public class BserSeasonDto {
    private int seasonId;
    private String seasonName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isCurrent;

}