package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "BSER 유저 검색 응답 Wrapper")
public class BserUserResponse<T> {
    
    @Schema(description = "응답 코드")
    @JsonProperty("code")
    private Integer code;

    @Schema(description = "메시지")
    @JsonProperty("message")
    private String message;

    @Schema(description = "유저 검색 결과 리스트")
    @JsonProperty("user")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<T> user;
    
    // 단일 객체 응답도 처리할 수 있도록 추가
    @JsonProperty("userStats")
    private List<T> userStats;
    
    // 다양한 응답 형태 처리
    @JsonProperty("userNum")
    private Long userNum;
    
    @JsonProperty("next")
    private Long next;
    
    /**
     * 유저 리스트 반환 (다양한 필드명 지원)
     */
    public List<T> getUser() {
        if (user != null && !user.isEmpty()) {
            return user;
        }
        if (userStats != null && !userStats.isEmpty()) {
            return userStats;
        }
        return List.of();
    }
}
