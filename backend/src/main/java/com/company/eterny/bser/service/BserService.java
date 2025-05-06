package com.company.eterny.bser.service;

import com.company.eterny.bser.dto.BserUserResponse;
import com.company.eterny.bser.dto.NicknameData;
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

@Service
public class BserService {
    private final RestTemplate restTemplate;

    @Value("${bser.api.key}")
    private String apiKey;

    public BserService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
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
            ResponseEntity<BserUserResponse<NicknameData>> resp = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<BserUserResponse<NicknameData>>() {}
            );

            BserUserResponse<NicknameData> body = resp.getBody();
            if (body != null && body.getUser() != null) {
                return List.of(body.getUser());
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
