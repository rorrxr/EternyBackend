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
@Schema(description = "게임 전적 정보 DTO")
public class BserGameDto {

    @Schema(description = "유저 고유 번호")
    @JsonProperty("userNum")
    private Long userNum;

    @Schema(description = "닉네임")
    @JsonProperty("nickname")
    private String nickname;

    @Schema(description = "게임 ID")
    @JsonProperty("gameId")
    private Long gameId;

    @Schema(description = "시즌 ID")
    @JsonProperty("seasonId")
    private Integer seasonId;

    @Schema(description = "매칭 모드 (2: 일반, 3: 랭크)")
    @JsonProperty("matchingMode")
    private Integer matchingMode;

    @Schema(description = "팀 매칭 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)")
    @JsonProperty("matchingTeamMode")
    private Integer matchingTeamMode;

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

    @Schema(description = "플레이어 킬 수")
    @JsonProperty("playerKill")
    private Integer playerKill;

    @Schema(description = "플레이어 데스 수")
    @JsonProperty("playerDeaths")
    private Integer playerDeaths;

    @Schema(description = "어시스트 수")
    @JsonProperty("playerAssistant")
    private Integer playerAssistant;

    @Schema(description = "몬스터 킬 수")
    @JsonProperty("monsterKill")
    private Integer monsterKill;

    @Schema(description = "주 무기 종류")
    @JsonProperty("bestWeapon")
    private Integer bestWeapon;

    @Schema(description = "주 무기 레벨")
    @JsonProperty("bestWeaponLevel")
    private Integer bestWeaponLevel;

    @Schema(description = "숙련도 레벨")
    @JsonProperty("masteryLevel")
    private Object masteryLevel;

    @Schema(description = "플레이어에게 준 데미지")
    @JsonProperty("damageToPlayer")
    private Integer damageToPlayer;

    @Schema(description = "플레이어에게 받은 데미지")
    @JsonProperty("damageFromPlayer")
    private Integer damageFromPlayer;

    @Schema(description = "힐량")
    @JsonProperty("healAmount")
    private Integer healAmount;

    @Schema(description = "생존 시간 (초)")
    @JsonProperty("survivalTime")
    private Integer survivalTime;

    @Schema(description = "관전 시간 (초)")
    @JsonProperty("watchTime")
    private Integer watchTime;

    @Schema(description = "총 시간 (초)")
    @JsonProperty("totalTime")
    private Integer totalTime;

    @Schema(description = "추가된 봇 수")
    @JsonProperty("botAdded")
    private Integer botAdded;

    @Schema(description = "남은 봇 수")
    @JsonProperty("botRemain")
    private Integer botRemain;

    @Schema(description = "금지 구역")
    @JsonProperty("restrictedArea")
    private Integer restrictedArea;

    @Schema(description = "안전 지대")
    @JsonProperty("safeAreas")
    private Integer safeAreas;

    @Schema(description = "팀 킬")
    @JsonProperty("teamKill")
    private Integer teamKill;

    @Schema(description = "계정 레벨")
    @JsonProperty("accountLevel")
    private Integer accountLevel;

    @Schema(description = "오그멘트 레벨")
    @JsonProperty("augmentLevel")
    private Integer augmentLevel;

    @Schema(description = "승리 여부")
    @JsonProperty("victory")
    private Boolean victory;

    @Schema(description = "팀 모드")
    @JsonProperty("teamMode")
    private Integer teamMode;

    @Schema(description = "MMR 변화량")
    @JsonProperty("mmrGain")
    private Integer mmrGain;

    @Schema(description = "MMR 시작값")
    @JsonProperty("mmrBefore")
    private Integer mmrBefore;

    @Schema(description = "MMR 결과값")
    @JsonProperty("mmrAfter")
    private Integer mmrAfter;

    @Schema(description = "게임 시작 시간")
    @JsonProperty("startDtm")
    private String startDtm;

    @Schema(description = "탈출 상태")
    @JsonProperty("escapeState")
    private Integer escapeState;

    @Schema(description = "전술 스킬 그룹")
    @JsonProperty("tacticalSkillGroup")
    private Integer tacticalSkillGroup;

    @Schema(description = "전술 스킬 레벨")
    @JsonProperty("tacticalSkillLevel")
    private Integer tacticalSkillLevel;

    @Schema(description = "플레이 시간")
    @JsonProperty("playTime")
    private Integer playTime;

    @Schema(description = "게임 시간 (초)")
    @JsonProperty("duration")
    private Integer duration;

    @Schema(description = "장비 정보")
    @JsonProperty("equipment")
    private Object equipment;

    @Schema(description = "장비 등급 정보")
    @JsonProperty("equipmentGrade")
    private Object equipmentGrade;
}
