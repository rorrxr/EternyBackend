package com.company.eterny.infrastructure.external.bser.dto;

import com.company.eterny.domain.nickname.entity.Nickname;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "닉네임 검색 결과")
public class NicknameDto {
//    @Schema(description = "유저 고유 번호", example = "2560532")
//    private Long userNum;
//
//    @Schema(description = "유저 닉네임", example = "Hide on bush")
//    private String nickname;

    @JsonProperty("userNum")
    private Long userNum;

    @JsonProperty("nickname")
    private String nickname;

//    public static NicknameDto fromEntity(Nickname entity) {
//        NicknameDto dto = new NicknameDto();
//        dto.setUserNum(entity.getUserNum());
//        dto.setNickname(entity.getNickname());
//        return dto;
//    }
}