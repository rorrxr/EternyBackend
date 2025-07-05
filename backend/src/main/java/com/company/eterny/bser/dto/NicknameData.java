package com.company.eterny.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "닉네임 검색 결과")
public class NicknameData {
    @Schema(description = "유저 고유 번호", example = "2560532")
    private Long userNum;

    @Schema(description = "유저 닉네임", example = "Hide on bush")
    private String nickname;
}