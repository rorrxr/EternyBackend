package com.company.eterny.bser.service;

import com.company.eterny.bser.dto.*;
import com.company.eterny.global.dto.CommonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

@Service
public class BserService {
    private final RestTemplate rt;
    @Value("${bser.api.key}") private String apiKey;
    private final String BSER_BASE = "https://open-api.bser.io/v1";

    public BserService(RestTemplateBuilder b) {
        this.rt = b.build();
    }

    private HttpHeaders headers() {
        HttpHeaders h = new HttpHeaders();
        h.set("Accept", "application/json");
        h.set("x-api-key", apiKey);
        return h;
    }


    /** 게임 전적 가져오기 */
    public List<BserGameDto> getGamesByUser(Long userNum) {
        String url = BSER_BASE + "/user/games/" + userNum;
        HttpEntity<Void> ent = new HttpEntity<>(headers());
        ResponseEntity<BserGamesResponse> resp = rt.exchange(
                url, HttpMethod.GET, ent,
                new ParameterizedTypeReference<BserGamesResponse>() {}
        );
        BserGamesResponse body = resp.getBody();
        return (body != null && body.getUserGames() != null)
                ? body.getUserGames()
                : Collections.emptyList();
    }

    /**
     * /v1/user/games/{userNum} → data.userGames 를 꺼내서 List<BserGameDto> 로 반환
     */
//    public List<BserGameDto> getGamesByUser(Long userNum) {
//        String url = BSER_BASE + "/user/games/" + userNum;
//        HttpEntity<Void> ent = new HttpEntity<>(headers());
//
//        // BSER 의 data.userGames 를 BserGameData 에 매핑
//        ResponseEntity<CommonResponse<BserGameData>> resp =
//                rt.exchange(url, HttpMethod.GET, ent,
//                        new ParameterizedTypeReference<CommonResponse<BserGameData>>() {});
//
//        CommonResponse<BserGameData> body = resp.getBody();
//        if (body == null || body.getData() == null) {
//            return Collections.emptyList();
//        }
//        return body.getData().getUserGames();
//    }

    /** 랭크 정보 가져오기 */
    public BserRankDto getRankByUser(Long userNum, int seasonId, int mode) {
        String url = String.format("%s/rank/%d/%d/%d", BSER_BASE, userNum, seasonId, mode);
        HttpEntity<Void> ent = new HttpEntity<>(headers());
        ResponseEntity<BserRankResponse> resp = rt.exchange(
                url, HttpMethod.GET, ent,
                new ParameterizedTypeReference<BserRankResponse>() {}
        );
        BserRankResponse body = resp.getBody();
        return (body != null) ? body.getUserRank() : null;
    }

    /**
     * 닉네임으로 BSER API에서 사용자 리스트를 조회
     */

    public List<NicknameData> getUserByNickname(String nickname) {
        // 1) URL 구축
        String url = "https://open-api.bser.io/v1/user/nickname?query=" +
                UriUtils.encode(nickname, StandardCharsets.UTF_8);

        // 2) 헤더 세팅
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            // 3) 호출, 제네릭을 명시
            ResponseEntity<BserUserResponse<NicknameData>> resp = rt.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<BserUserResponse<NicknameData>>() {}
            );

            BserUserResponse<NicknameData> body = resp.getBody();
            if (body != null && body.getUser() != null) {
                // body.getUser()가 이미 List<NicknameData> 이므로,
                // 그대로 반환하거나 복사본을 반환하세요.
                return body.getUser();
                // 만약 변경 불변 리스트가 필요하다면:
                // return List.copyOf(body.getUser());
                // 또는
                // return new ArrayList<>(body.getUser());
            }
            return Collections.emptyList();
        } catch (HttpClientErrorException.NotFound ex) {
            // 404는 빈 리스트로 처리
            return Collections.emptyList();
        } catch (Exception ex) {
            throw new RuntimeException("BSER API 호출 실패", ex);
        }
    }
}
