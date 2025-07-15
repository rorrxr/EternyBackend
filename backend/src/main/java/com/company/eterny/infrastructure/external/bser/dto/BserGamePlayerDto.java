package com.company.eterny.infrastructure.external.bser.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "게임 참가자 정보")
public class BserGamePlayerDto {
    @Schema(description = "유저 번호")
    private Long userNum;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "캐릭터 번호")
    private Integer characterNum;

    @Schema(description = "스킨 코드")
    private Integer skinCode;

    @Schema(description = "캐릭터 레벨")
    private Integer characterLevel;

    @Schema(description = "게임 순위")
    private Integer gameRank;

    @Schema(description = "플레이어 킬")
    private Integer playerKill;

    @Schema(description = "어시스트")
    private Integer playerAssistant;

    @Schema(description = "몬스터 킬")
    private Integer monsterKill;

    @Schema(description = "주 무기")
    private Integer bestWeapon;

    @Schema(description = "주 무기 레벨")
    private Integer bestWeaponLevel;

    @Schema(description = "숙련도 정보")
    private Map<String, Integer> mastery;

    @Schema(description = "장비 정보")
    private Map<String, Integer> equipment;

    @Schema(description = "시작 시간")
    private String startDtm;

    @Schema(description = "플레이 시간")
    private Integer playTime;

    @Schema(description = "MMR 시작값")
    private Double mmrBefore;

    @Schema(description = "MMR 증감")
    private Integer mmrGain;

    @Schema(description = "MMR 최종값")
    private Double mmrAfter;

    @Schema(description = "팀 번호")
    private Integer teamNumber;

    @Schema(description = "사망 원인")
    private String causeOfDeath;
}