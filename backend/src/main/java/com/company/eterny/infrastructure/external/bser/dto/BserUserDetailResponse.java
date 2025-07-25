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
@Schema(description = "BSER 유저 상세 통계 응답")
public class BserUserDetailResponse {
    @Schema(description = "응답 코드")
    private Integer code;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "유저 상세 통계 (첫 번째 매칭모드)")
    private BserUserDetailDto user;
    
    @Schema(description = "유저 상세 통계 목록 (모든 매칭모드)")
    private List<BserUserDetailDto> userStats;
    
    /**
     * 첫 번째 유저 통계를 반환 (하위호환)
     */
    public BserUserDetailDto getUser() {
        if (user != null) {
            return user;
        }
        if (userStats != null && !userStats.isEmpty()) {
            return userStats.get(0);
        }
        return null;
    }
}