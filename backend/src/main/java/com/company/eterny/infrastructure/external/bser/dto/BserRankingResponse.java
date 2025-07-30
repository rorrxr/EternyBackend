package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "랭킹 데이터 응답")
public class BserRankingResponse {
    @Schema(description = "응답 코드")
    private Integer code;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "상위 랭킹 데이터 리스트")
    @JsonProperty("topRanks")
    private List<BserRankingDto> topRanks;

    @Schema(description = "다음 페이지 커서")
    @JsonProperty("next")
    private Integer next;
} 