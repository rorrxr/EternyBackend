package com.company.eterny.bser.controller;


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

    @GetMapping("/user/nickname")
    public CommonResponse<List<NicknameData>> searchByNickname(
            @RequestParam("query") String query) {
        System.out.println("searchByNickname called, query=" + query);

        List<NicknameData> list = bserService.getUserByNickname(query);
        return new CommonResponse<>(200, "Success", list);
    }

    @GetMapping("/games/{userNum}")
    public ResponseEntity<Object> getGames(@PathVariable Long userNum) {
        String url = String.format("%s/user/games/%d", BSER_BASE, userNum);
        return rt.exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers()), Object.class
        );
    }
}