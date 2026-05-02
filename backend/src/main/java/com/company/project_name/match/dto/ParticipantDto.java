package com.company.project_name.match.dto;

import com.company.project_name.external.bser.dto.BserGameDetailResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParticipantDto {

    private long userNum;
    private String nickname;
    private int characterId;
    private int gameRank;
    private int kills;
    private int assists;
    private int damageDealt;
    private int damageTaken;
    private int healAmount;
    private long mmrBefore;
    private long mmrAfter;
    private long mmrGain;
    private List<Integer> equipment;

    public static ParticipantDto fromBser(BserGameDetailResponseDto.BserGamePlayer player) {
        return ParticipantDto.builder()
                .userNum(player.getUserNum())
                .nickname(player.getNickname())
                .characterId(player.getCharacterNum())
                .gameRank(player.getGameRank())
                .kills(player.getPlayerKill())
                .assists(player.getPlayerAssistant())
                .damageDealt(player.getDamageDealt())
                .damageTaken(player.getDamageTaken())
                .healAmount(player.getHealAmount())
                .mmrBefore(player.getMmrBefore())
                .mmrAfter(player.getMmrAfter())
                .mmrGain(player.getMmrGain())
                .equipment(player.getEquipment())
                .build();
    }
}
