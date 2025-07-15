package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "간단한 KDA 정보")
public class BserSimpleKDADto {
//
//    @Schema(description = "유저 번호")
//    private Long userNum;
//
//    @Schema(description = "닉네임")
//    private String nickname;
//
//    @Schema(description = "게임 ID")
//    private Long gameId;
//
//    @Schema(description = "캐릭터 번호")
//    private Integer characterNum;
//
//    @Schema(description = "최종 순위")
//    private Integer gameRank;
//
//    @Schema(description = "킬 수")
//    private Integer playerKill;
//
//    @Schema(description = "어시스트 수")
//    private Integer playerAssistant;
//
//    @Schema(description = "데스 수")
//    private Integer playerDeaths;
//
//    @Schema(description = "몬스터 킬 수")
//    private Integer monsterKill;
//
//    @Schema(description = "플레이어에게 준 총 피해량")
//    private Integer damageToPlayer;
//
//    @Schema(description = "플레이어에게서 받은 총 피해량")
//    private Integer damageFromPlayer;
//
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
//    // 계산된 필드
//    @Schema(description = "KDA 비율")
//    public Double getKdaRatio() {
//        if (playerDeaths == null || playerDeaths == 0) {
//            return (playerKill != null ? playerKill : 0) + (playerAssistant != null ? playerAssistant : 0.0);
//        }
//        return ((playerKill != null ? playerKill : 0) + (playerAssistant != null ? playerAssistant : 0.0)) / playerDeaths;
//    }
//
//    @Schema(description = "분당 피해량")
//    public Double getDamagePerMinute() {
//        if (playTime == null || playTime == 0) {
//            return 0.0;
//        }
//        return (damageToPlayer != null ? damageToPlayer : 0.0) / (playTime / 60.0);
//    }
//
//    @Schema(description = "승리 여부 (boolean)")
//    public Boolean isWin() {
//        return victory != null && victory == 1;
//    }

    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("playerKill")
    private Integer playerKill;

    @JsonProperty("playerDeaths")
    private Integer playerDeaths;

    @JsonProperty("playerAssistant")
    private Integer playerAssistant;

    @JsonProperty("gameRank")
    private Integer gameRank;

    @JsonProperty("isWin")
    private Boolean isWin;

    @JsonProperty("characterNum")
    private Integer characterNum;

    public Boolean isWin() {
        return isWin;
    }
}