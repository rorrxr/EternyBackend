package com.company.eterny.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "게임 전적 정보 DTO")
public class BserGameDto {
    @Schema(description = "유저 고유 번호")
    private Long userNum;

    @Schema(description = "유저 닉네임")
    private String nickname;

    @Schema(description = "게임 ID")
    private Long gameId;

    @Schema(description = "시즌 ID")
    private Integer seasonId;

    @Schema(description = "매칭 모드")
    private Integer matchingMode;

    @Schema(description = "팀 매칭 모드")
    private Integer matchingTeamMode;

    @Schema(description = "캐릭터 번호")
    private Integer characterNum;

    @Schema(description = "스킨 코드")
    private Integer skinCode;

    @Schema(description = "캐릭터 레벨")
    private Integer characterLevel;

    @Schema(description = "게임 순위")
    private Integer gameRank;

    @Schema(description = "플레이어 킬 수")
    private Integer playerKill;

    @Schema(description = "어시스트 수")
    private Integer playerAssistant;

    @Schema(description = "몬스터 킬 수")
    private Integer monsterKill;

    @Schema(description = "주 무기 종류")
    private Integer bestWeapon;

    @Schema(description = "주 무기 레벨")
    private Integer bestWeaponLevel;

    @Schema(description = "숙련도 레벨")
    private Map<String,Integer> masteryLevel;

    @Schema(description = "장비 정보")
    private Map<String,Integer> equipment;

    @Schema(description = "장비 등급 정보")
    private Map<String,Integer> equipmentGrade;

    @Schema(description = "게임 시작 시간")
    private String startDtm;

    @Schema(description = "게임 시간 (초)")
    private Integer duration;

    @Schema(description = "MMR 시작값")
    private Integer mmrBefore;

    @Schema(description = "MMR 증가량")
    private Integer mmrGain;

    @Schema(description = "MMR 결과값")
    private Integer mmrAfter;
}
