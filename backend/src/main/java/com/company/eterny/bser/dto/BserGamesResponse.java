package com.company.eterny.bser.dto;

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
@Schema(description = "BSER 전적 응답 Wrapper")
public class BserGamesResponse {
    @Schema(description = "응답 코드")
    private int code;

    @Schema(description = "메시지")
    private String message;

    @Schema(description = "게임 전적 리스트")
    private List<BserGameDto> userGames;

    @Schema(description = "다음 페이지를 위한 next cursor")
    private Long next;
}
