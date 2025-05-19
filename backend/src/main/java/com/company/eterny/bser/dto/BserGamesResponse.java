package com.company.eterny.bser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// BSER API 의 data.userGames 만 꺼내줄 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BserGamesResponse {
    private int code;
    private String message;
    private List<BserGameDto> userGames;
    private Long next;
}
