package com.company.project_name.external.bser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BserUserNicknameResponseDto {

    private int code;
    private String message;
    private BserUserInfo user;

    @Data
    @NoArgsConstructor
    public static class BserUserInfo {
        private long userNum;
        private String nickname;
    }
}
