package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("code")
    private Integer code;

    @Schema(description = "메시지")
    @JsonProperty("message")
    private String message;

    @Schema(description = "게임 전적 리스트")
    @JsonProperty("userGames")
    private List<BserGameDto> userGames;

    @Schema(description = "다음 페이지를 위한 next cursor")
    @JsonProperty("next")
    private Long next;
    
    // 다른 필드명으로 응답이 올 수 있는 경우를 대비
    @JsonProperty("games")
    private List<BserGameDto> games;
    
    @JsonProperty("userStats")
    private List<BserGameDto> userStats;
    
    /**
     * 게임 리스트 반환 (다양한 필드명 지원)
     */
    public List<BserGameDto> getUserGames() {
        if (userGames != null && !userGames.isEmpty()) {
            return userGames;
        }
        if (games != null && !games.isEmpty()) {
            return games;
        }
        if (userStats != null && !userStats.isEmpty()) {
            return userStats;
        }
        return List.of();
    }
}
