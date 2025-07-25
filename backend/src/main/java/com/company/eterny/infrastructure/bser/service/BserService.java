package com.company.eterny.infrastructure.bser.service;

import com.company.eterny.infrastructure.external.bser.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * BSER API 연동 서비스
 */
@Service
@RequiredArgsConstructor
public class BserService {

    private final RestTemplate rt;

    @Value("${bser.api.key}")
    private String apiKey;

    private final String BSER_BASE = "https://open-api.bser.io/v1";

    private HttpHeaders headers() {
        HttpHeaders h = new HttpHeaders();
        h.set("Accept", "application/json");
        h.set("x-api-key", apiKey);
        return h;
    }

    /** 닉네임으로 유저 검색 */
    public List<NicknameDto> getUserByNickname(String nickname) {
        String url = BSER_BASE + "/user/nickname?query=" +
                UriUtils.encode(nickname, StandardCharsets.UTF_8);
        HttpEntity<Void> entity = new HttpEntity<>(headers());

        try {
            ResponseEntity<BserUserResponse<NicknameDto>> resp = rt.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<>() {}
            );

            BserUserResponse<NicknameDto> body = resp.getBody();

            return (body != null && body.getUser() != null)
                    ? body.getUser()
                    : Collections.emptyList();

        } catch (HttpClientErrorException.NotFound ex) {
            System.out.println("[BSER API] 닉네임 검색 결과 없음:");
            return Collections.emptyList();
        } catch (Exception ex) {
            System.out.println("[BSER API] 닉네임 검색 실패:");
            throw new RuntimeException("BSER API 호출 실패: " + ex.getMessage());
        }
    }

    /** 게임 전적 가져오기 */
    public List<BserGameDto> getGamesByUser(Long userNum) {
        String url = BSER_BASE + "/user/games/" + userNum;
        HttpEntity<Void> ent = new HttpEntity<>(headers());

        try {
            ResponseEntity<BserGamesResponse> resp = rt.exchange(
                    url, HttpMethod.GET, ent,
                    new ParameterizedTypeReference<BserGamesResponse>() {}
            );

            BserGamesResponse body = resp.getBody();
            return (body != null && body.getUserGames() != null)
                    ? body.getUserGames()
                    : Collections.emptyList();

        } catch (Exception ex) {
            throw new RuntimeException("게임 전적 조회 실패: " + ex.getMessage());
        }
    }

    /** 랭크 정보 가져오기 */
    public BserRankDto getRankByUser(Long userNum, int seasonId, int mode) {
        String url = String.format("%s/rank/%d/%d/%d", BSER_BASE, userNum, seasonId, mode);
        HttpEntity<Void> ent = new HttpEntity<>(headers());

        try {
            ResponseEntity<BserRankResponse> resp = rt.exchange(
                    url, HttpMethod.GET, ent,
                    new ParameterizedTypeReference<BserRankResponse>() {}
            );

            BserRankResponse body = resp.getBody();
            return (body != null) ? body.getUserRank() : null;

        } catch (Exception ex) {
            throw new RuntimeException("랭크 정보 조회 실패: " + ex.getMessage());
        }
    }
}
