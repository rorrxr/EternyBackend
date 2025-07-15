package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "아이템 마스터 데이터")
public class BserItemDto {
    @Schema(description = "아이템 코드")
    private Integer code;

    @Schema(description = "아이템 이름")
    private String name;

    @Schema(description = "영문 이름")
    private String engName;

    @Schema(description = "아이템 타입")
    private Integer itemType;

    @Schema(description = "아이템 등급")
    private Integer itemGrade;

    @Schema(description = "스택 가능 여부")
    private String stackable;

    @Schema(description = "초기 개수")
    private Integer initialCount;

    @Schema(description = "소비 가능 여부")
    private String consumable;

    @Schema(description = "제작 재료1")
    private Integer makeMaterial1;

    @Schema(description = "제작 재료2")
    private Integer makeMaterial2;

    /**
     * 아이템 ID 반환 (API 호환성)
     */
    public Integer getItemId() {
        return code;
    }

    /**
     * 아이템 이름 반환 (API 호환성)
     */
    public String getItemName() {
        return name;
    }

    /**
     * 아이템 등급 문자열 반환
     */
    public String getItemGrade() {
        if (itemGrade == null) return "Normal";
        return switch (itemGrade) {
            case 1 -> "Normal";
            case 2 -> "Uncommon";
            case 3 -> "Rare";
            case 4 -> "Epic";
            case 5 -> "Legendary";
            default -> "Normal";
        };
    }
}