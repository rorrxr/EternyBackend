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
@Schema(description = "BSER 무기 정보")
public class BserWeaponDto {
    
    @Schema(description = "무기 코드")
    @JsonProperty("code")
    private Integer code;

    @Schema(description = "무기 이름")
    @JsonProperty("name")
    private String name;

    @Schema(description = "영문 이름")
    @JsonProperty("engName")
    private String engName;

    @Schema(description = "무기 타입")
    @JsonProperty("weaponType")
    private Integer weaponType;

    @Schema(description = "공격 타입")
    @JsonProperty("attackType")
    private String attackType;

    @Schema(description = "공격 범위")
    @JsonProperty("attackRange")
    private String attackRange;

    @Schema(description = "공격 속도")
    @JsonProperty("attackSpeed")
    private String attackSpeed;

    @Schema(description = "피해량")
    @JsonProperty("damage")
    private String damage;

    /**
     * 무기 ID 반환 (API 호환성)
     */
    public Integer getWeaponId() {
        return code;
    }

    /**
     * 무기 이름 반환 (API 호환성)
     */
    public String getWeaponName() {
        return name;
    }
} 