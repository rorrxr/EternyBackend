package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "닉네임 검색 결과")
public class NicknameDto {
    
    @Schema(description = "유저 고유 번호", example = "2560532")
    @JsonProperty("userNum")
    private Long userNum;

    @Schema(description = "유저 닉네임", example = "Hide on bush")
    @JsonProperty("nickname") 
    private String nickname;
    
    // 추가 필드들 (실제 API 응답에 포함될 수 있는 필드들)
    @JsonProperty("mmr")
    private Integer mmr;
    
    @JsonProperty("rank")
    private Integer rank;
    
    @JsonProperty("seasonId")
    private Integer seasonId;
    
    @JsonProperty("matchingTeamMode")
    private Integer matchingTeamMode;
}
