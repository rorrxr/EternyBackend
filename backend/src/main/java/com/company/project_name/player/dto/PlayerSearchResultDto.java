package com.company.project_name.player.dto;

import com.company.project_name.external.bser.dto.BserUserNicknameResponseDto;
import com.company.project_name.player.entity.PlayerProfile;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@Builder
public class PlayerSearchResultDto {

    private long userNum;
    private String nickname;
    private String tier;
    private Integer rank;
    private String updatedAt;

    public static PlayerSearchResultDto fromProfile(PlayerProfile profile) {
        return PlayerSearchResultDto.builder()
                .userNum(profile.getUserNum())
                .nickname(profile.getNickname())
                .updatedAt(profile.getUpdatedAt() != null
                        ? profile.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        : null)
                .build();
    }

    public static PlayerSearchResultDto fromBser(BserUserNicknameResponseDto.BserUserInfo info) {
        return PlayerSearchResultDto.builder()
                .userNum(info.getUserNum())
                .nickname(info.getNickname())
                .build();
    }
}
