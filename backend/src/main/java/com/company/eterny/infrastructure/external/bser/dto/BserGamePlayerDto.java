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
@Schema(description = "게임 참가자 정보")
public class BserGamePlayerDto {
    
    @Schema(description = "유저 번호")
    @JsonProperty("userNum")
    private Long userNum;

    @Schema(description = "닉네임")
    @JsonProperty("nickname")
    private String nickname;

    @Schema(description = "캐릭터 번호")
    @JsonProperty("characterNum")
    private Integer characterNum;

    @Schema(description = "스킨 코드")
    @JsonProperty("skinCode")
    private Integer skinCode;

    @Schema(description = "캐릭터 레벨")
    @JsonProperty("characterLevel")
    private Integer characterLevel;

    @Schema(description = "게임 순위")
    @JsonProperty("gameRank")
    private Integer gameRank;

    @Schema(description = "플레이어 킬")
    @JsonProperty("playerKill")
    private Integer playerKill;

    @Schema(description = "플레이어 데스")
    @JsonProperty("playerDeaths")
    private Integer playerDeaths;

    @Schema(description = "어시스트")
    @JsonProperty("playerAssistant")
    private Integer playerAssistant;

    @Schema(description = "몬스터 킬")
    @JsonProperty("monsterKill")
    private Integer monsterKill;

    @Schema(description = "주 무기")
    @JsonProperty("bestWeapon")
    private Integer bestWeapon;

    @Schema(description = "주 무기 레벨")
    @JsonProperty("bestWeaponLevel")
    private Integer bestWeaponLevel;

    @Schema(description = "플레이어에게 준 데미지")
    @JsonProperty("damageToPlayer")
    private Integer damageToPlayer;

    @Schema(description = "플레이어에게 받은 데미지")
    @JsonProperty("damageFromPlayer")
    private Integer damageFromPlayer;

    @Schema(description = "힐량")
    @JsonProperty("healAmount")
    private Integer healAmount;

    @Schema(description = "생존 시간")
    @JsonProperty("survivalTime")
    private Integer survivalTime;

    @Schema(description = "관전 시간")
    @JsonProperty("watchTime")
    private Integer watchTime;

    @Schema(description = "게임 시작 시간")
    @JsonProperty("startDtm")
    private String startDtm;

    @Schema(description = "플레이 시간")
    @JsonProperty("playTime")
    private Integer playTime;

    @Schema(description = "MMR 시작값")
    @JsonProperty("mmrBefore")
    private Integer mmrBefore;

    @Schema(description = "MMR 증감")
    @JsonProperty("mmrGain")
    private Integer mmrGain;

    @Schema(description = "MMR 최종값")
    @JsonProperty("mmrAfter")
    private Integer mmrAfter;

    @Schema(description = "팀 번호")
    @JsonProperty("teamNumber")
    private Integer teamNumber;

    @Schema(description = "승리 여부")
    @JsonProperty("victory")
    private Boolean victory;

    @Schema(description = "탈출 상태")
    @JsonProperty("escapeState")
    private Integer escapeState;

    @Schema(description = "팀 킬")
    @JsonProperty("teamKill")
    private Integer teamKill;

    @Schema(description = "전술 스킬 그룹")
    @JsonProperty("tacticalSkillGroup")
    private Integer tacticalSkillGroup;

    @Schema(description = "전술 스킬 레벨")
    @JsonProperty("tacticalSkillLevel")
    private Integer tacticalSkillLevel;

    @Schema(description = "숙련도 정보")
    @JsonProperty("masteryLevel")
    private Object masteryLevel;

    @Schema(description = "장비 정보")
    @JsonProperty("equipment")
    private Object equipment;

    @Schema(description = "장비 등급 정보")
    @JsonProperty("equipmentGrade")
    private Object equipmentGrade;
}
