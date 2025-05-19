package com.company.eterny.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BserGameDto {
    private Long userNum;
    private String nickname;
    private Long gameId;
    private Integer seasonId;
    private Integer matchingMode;
    private Integer matchingTeamMode;
    private Integer characterNum;
    private Integer skinCode;
    private Integer characterLevel;
    private Integer gameRank;
    private Integer playerKill;
    private Integer playerAssistant;
    private Integer monsterKill;
    private Integer bestWeapon;
    private Integer bestWeaponLevel;
    private Map<String,Integer> masteryLevel;
    private Map<String,Integer> equipment;
    private Map<String,Integer> equipmentGrade;
    private String startDtm;
    private Integer duration;
    private Integer mmrBefore;
    private Integer mmrGain;
    private Integer mmrAfter;
}