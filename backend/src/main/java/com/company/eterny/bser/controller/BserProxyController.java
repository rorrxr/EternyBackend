package com.company.eterny.bser.controller;


import com.company.eterny.bser.dto.BserGameDto;
import com.company.eterny.bser.dto.BserRankDto;
import com.company.eterny.bser.dto.NicknameData;
import com.company.eterny.bser.service.BserService;
import com.company.eterny.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bser")
@CrossOrigin(origins = "http://localhost:3000")
public class BserProxyController {

    @Autowired
    private final RestTemplate rt;

    @Autowired
    private final BserService bserService;

    @Value("${bser.api.key}")
    private String apiKey;
    private final String BSER_BASE = "https://open-api.bser.io/v1";

    private HttpHeaders headers() {
        HttpHeaders h = new HttpHeaders();
        h.set("Accept", "application/json");
        h.set("x-api-key", apiKey);
        return h;
    }

    /** 닉네임 검색 **/
    @GetMapping("/user/nickname")
    public CommonResponse<List<NicknameData>> searchUserByNickname(
            @RequestParam("query") String query) {
        List<NicknameData> users = bserService.getUserByNickname(query);
        return new CommonResponse<>(200, "Success", users);
    }

    /** 전적 검색 **/
    @GetMapping("/games/{userNum}")
    public CommonResponse<List<BserGameDto>> getGames(@PathVariable Long userNum) {
        List<BserGameDto> games = bserService.getGamesByUser(userNum);
        // new CommonResponse<>(code, message, data) 를 쓰면 code 필드에 200이,
        // status 필드는 기본값인 0이 들어갑니다.
        return new CommonResponse<>(200, "Success", games);
    }

    /** 랭크 검색 **/
    @GetMapping("/rank/{userNum}/{seasonId}/{mode}")
    public CommonResponse<BserRankDto> getRank(
            @PathVariable Long userNum,
            @PathVariable int seasonId,
            @PathVariable int mode
    ) {
        BserRankDto rank = bserService.getRankByUser(userNum, seasonId, mode);
        return new CommonResponse<>(200, "Success", rank);
    }
}