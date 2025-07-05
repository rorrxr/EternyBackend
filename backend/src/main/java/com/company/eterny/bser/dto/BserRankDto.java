package com.company.eterny.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "BSER 랭크 정보 DTO")
public class BserRankDto {
    @Schema(description = "유저 번호")
    private Long userNum;

    @Schema(description = "서버 코드")
    private Integer serverCode;

    @Schema(description = "MMR 점수")
    private Integer mmr;

    @Schema(description = "서버 랭킹")
    private Integer serverRank;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "전체 랭크")
    private Integer rank;
}