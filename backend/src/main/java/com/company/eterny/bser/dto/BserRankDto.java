package com.company.eterny.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BserRankDto {
    private Long userNum;
    private Integer serverCode;
    private Integer mmr;
    private Integer serverRank;
    private String nickname;
    private Integer rank;
}
