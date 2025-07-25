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
@Schema(description = "게임 상세 정보 응답")
public class BserGameDetailResponse {
    
    @Schema(description = "응답 코드")
    @JsonProperty("code")
    private Integer code;

    @Schema(description = "응답 메시지")
    @JsonProperty("message")
    private String message;

    @Schema(description = "게임에 참여한 모든 유저의 상세 정보 목록")
    @JsonProperty("userGames")
    private List<BserGameDetailDto> userGames;
    
    /**
     * 첫 번째 게임 정보를 반환 (하위호환)
     * 실제로는 userGames 리스트를 사용하는 것이 맞다
     */
    public BserGameDetailDto getGame() {
        if (userGames != null && !userGames.isEmpty()) {
            // 첫 번째 유저의 게임 정보만 반환
            return userGames.get(0);
        }
        return null;
    }
    
    /**
     * 모든 게임 참가자 정보 반환
     */
    public List<BserGameDetailDto> getUserGames() {
        return userGames;
    }
}
