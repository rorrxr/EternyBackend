package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "BSER 게임 상세 정보")
public class BserGameDetailDto {


    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("seasonId")
    private Integer seasonId;

    @JsonProperty("matchingMode")
    private Integer matchingMode;

    @JsonProperty("matchingTeamMode")
    private Integer matchingTeamMode;

    @JsonProperty("userNum")
    private Long userNum;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("characterNum")
    private Integer characterNum;

    @JsonProperty("gameRank")
    private Integer gameRank;

    @JsonProperty("playerKill")
    private Integer playerKill;

    @JsonProperty("playerDeaths")
    private Integer playerDeaths;

    @JsonProperty("playerAssistant")
    private Integer playerAssistant;

    @JsonProperty("monsterKill")
    private Integer monsterKill;

    @JsonProperty("damageToPlayer")
    private Integer damageToPlayer;

    @JsonProperty("damageFromPlayer")
    private Integer damageFromPlayer;

    @JsonProperty("healAmount")
    private Integer healAmount;

    @JsonProperty("victory")
    private Boolean victory;

    @JsonProperty("escapeState")
    private Integer escapeState;

    @JsonProperty("mmrGain")
    private Integer mmrGain;

    @JsonProperty("startDtm")
    private String startDtm;

    @JsonProperty("playTime")
    private Integer playTime;

    @JsonProperty("tacticalSkillGroup")
    private Integer tacticalSkillGroup;

    @JsonProperty("tacticalSkillLevel")
    private Integer tacticalSkillLevel;

    @JsonProperty("traitFirstCore")
    private Integer traitFirstCore;

    @JsonProperty("traitFirstSub")
    private Integer traitFirstSub;

    @JsonProperty("traitSecondSub")
    private Integer traitSecondSub;

    @JsonProperty("teamNumber")
    private Integer teamNumber;

    @JsonProperty("versionMajor")
    private Integer versionMajor;

    @JsonProperty("versionMinor")
    private Integer versionMinor;

    @JsonProperty("botAdded")
    private Integer botAdded;

    @JsonProperty("botRemain")
    private Integer botRemain;
    // 기본 정보
//    @Schema(description = "유저 번호")
//    private Long userNum;
//
//    @Schema(description = "닉네임")
//    private String nickname;
//
//    @Schema(description = "게임 ID")
//    private Long gameId;
//
//    @Schema(description = "시즌 ID")
//    private Integer seasonId;
//
//    @Schema(description = "매칭 모드")
//    private Integer matchingMode;
//
//    @Schema(description = "팀 모드")
//    private Integer matchingTeamMode;
//
//    // 캐릭터 정보
//    @Schema(description = "캐릭터 번호")
//    private Integer characterNum;
//
//    @Schema(description = "캐릭터 레벨")
//    private Integer characterLevel;
//
//    // 게임 결과 - KDA 핵심 필드들
//    @Schema(description = "최종 순위")
//    private Integer gameRank;
//
//    @Schema(description = "플레이어 킬 수")
//    private Integer playerKill;
//
//    @Schema(description = "플레이어 어시스트 수")
//    private Integer playerAssistant;
//
//    @Schema(description = "플레이어 데스 수")
//    private Integer playerDeaths;
//
//    @Schema(description = "몬스터 킬 수")
//    private Integer monsterKill;
//
//    // 피해량 정보
//    @Schema(description = "플레이어에게 준 총 피해량")
//    private Integer damageToPlayer;
//
//    @Schema(description = "플레이어에게서 받은 총 피해량")
//    private Integer damageFromPlayer;
//
//    // 기타 주요 정보
//    @Schema(description = "힐량")
//    private Integer healAmount;
//
//    @Schema(description = "승리 여부")
//    private Integer victory;
//
//    @Schema(description = "MMR 변동량")
//    private Integer mmrGain;
//
//    @Schema(description = "플레이 시간 (초)")
//    private Integer playTime;
//
//    // 무기 및 장비 (기본)
//    @Schema(description = "최고 무기")
//    private Integer bestWeapon;
//
//    @Schema(description = "최고 무기 레벨")
//    private Integer bestWeaponLevel;
//
//    @Schema(description = "장비 정보")
//    private Map<String, Integer> equipment;
//
//    // 게임 메타 정보
//    @Schema(description = "게임 시작 시간")
//    private String startDtm;
//
//    @Schema(description = "게임 지속 시간")
//    private Integer duration;
//
//    @Schema(description = "팀 번호")
//    private Integer teamNumber;
//
//    @Schema(description = "사전 구성 팀원 수")
//    private Integer preMade;
}