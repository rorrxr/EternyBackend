package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "게임 전적 정보 DTO")
public class BserGameDto {
//    @Schema(description = "유저 고유 번호")
//    private Long userNum;
//
//    @Schema(description = "유저 닉네임")
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
//    @Schema(description = "팀 매칭 모드")
//    private Integer matchingTeamMode;
//
//    @Schema(description = "캐릭터 번호")
//    private Integer characterNum;
//
//    @Schema(description = "스킨 코드")
//    private Integer skinCode;
//
//    @Schema(description = "캐릭터 레벨")
//    private Integer characterLevel;
//
//    @Schema(description = "게임 순위")
//    private Integer gameRank;
//
//    @Schema(description = "플레이어 킬 수")
//    private Integer playerKill;
//
//    @Schema(description = "어시스트 수")
//    private Integer playerAssistant;
//
//    @Schema(description = "몬스터 킬 수")
//    private Integer monsterKill;
//
//    @Schema(description = "주 무기 종류")
//    private Integer bestWeapon;
//
//    @Schema(description = "주 무기 레벨")
//    private Integer bestWeaponLevel;
//
//    @Schema(description = "숙련도 레벨")
//    private Map<String,Integer> masteryLevel;
//
//    @Schema(description = "장비 정보")
//    private Map<String,Integer> equipment;
//
//    @Schema(description = "장비 등급 정보")
//    private Map<String,Integer> equipmentGrade;
//
//    @Schema(description = "게임 시작 시간")
//    private String startDtm;
//
//    @Schema(description = "게임 시간 (초)")
//    private Integer duration;
//
//    @Schema(description = "MMR 시작값")
//    private Integer mmrBefore;
//
//    @Schema(description = "MMR 증가량")
//    private Integer mmrGain;
//
//    @Schema(description = "MMR 결과값")
//    private Integer mmrAfter;

    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("seasonId")
    private Integer seasonId;

    @JsonProperty("matchingMode")
    private Integer matchingMode;

    @JsonProperty("matchingTeamMode")
    private Integer matchingTeamMode;

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

    @JsonProperty("bestWeapon")
    private Integer bestWeapon;

    @JsonProperty("bestWeaponLevel")
    private Integer bestWeaponLevel;

    @JsonProperty("masteryLevel")
    private Object masteryLevel;

    @JsonProperty("characterLevel")
    private Integer characterLevel;

    @JsonProperty("damageToPlayer")
    private Integer damageToPlayer;

    @JsonProperty("damageFromPlayer")
    private Integer damageFromPlayer;

    @JsonProperty("healAmount")
    private Integer healAmount;

    @JsonProperty("survivalTime")
    private Integer survivalTime;

    @JsonProperty("watchTime")
    private Integer watchTime;

    @JsonProperty("totalTime")
    private Integer totalTime;

    @JsonProperty("botAdded")
    private Integer botAdded;

    @JsonProperty("botRemain")
    private Integer botRemain;

    @JsonProperty("restrictedArea")
    private Integer restrictedArea;

    @JsonProperty("safeAreas")
    private Integer safeAreas;

    @JsonProperty("teamKill")
    private Integer teamKill;

    @JsonProperty("accountLevel")
    private Integer accountLevel;

    @JsonProperty("augmentLevel")
    private Integer augmentLevel;

    @JsonProperty("victory")
    private Boolean victory;

    @JsonProperty("teamMode")
    private Integer teamMode;

    @JsonProperty("mmrGain")
    private Integer mmrGain;

    @JsonProperty("startDtm")
    private String startDtm;

    @JsonProperty("escapeState")
    private Integer escapeState;

    @JsonProperty("tacticalSkillGroup")
    private Integer tacticalSkillGroup;

    @JsonProperty("tacticalSkillLevel")
    private Integer tacticalSkillLevel;

    @JsonProperty("playTime")
    private Integer playTime;

    @JsonProperty("nickname")
    private String nickname;
//    @Schema(description = "게임 ID")
//    private Long gameId;
//
//    @Schema(description = "닉네임")
//    private String nickname;

//    // getter 메서드들
//    public Long getGameId() {
//        return gameId;
//    }
//
//    public void setGameId(Long gameId) {
//        this.gameId = gameId;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
}
