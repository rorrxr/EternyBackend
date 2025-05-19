package com.company.eterny.bser.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BserGameData {
    private List<BserGameDto> userGames;
}