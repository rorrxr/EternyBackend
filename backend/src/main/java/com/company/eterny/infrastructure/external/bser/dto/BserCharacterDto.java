package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "캐릭터 마스터 데이터")
public class BserCharacterDto {
    @Schema(description = "캐릭터 코드")
    private Integer code;

    @Schema(description = "캐릭터 이름")
    private String name;

    @Schema(description = "영문 이름")
    private String engName;

    @Schema(description = "리소스 경로")
    private String resource;

    @Schema(description = "공격 타입")
    private Integer attackType;

    @Schema(description = "주 특성")
    private Integer mastery1;

    @Schema(description = "부 특성")
    private Integer mastery2;

    /**
     * 캐릭터 번호 반환 (API 호환성)
     */
    public Integer getCharacterNum() {
        return code;
    }

    /**
     * 캐릭터 이름 반환 (API 호환성)
     */
    public String getCharacterName() {
        return name;
    }
}
