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
@Schema(description = "BSER 유저 기본 정보")
public class BserUserDto {
    
    @Schema(description = "유저 고유 번호", example = "2560532")
    @JsonProperty("userNum")
    private Long userNum;

    @Schema(description = "유저 닉네임", example = "Hide on bush")
    @JsonProperty("nickname") 
    private String nickname;
} 