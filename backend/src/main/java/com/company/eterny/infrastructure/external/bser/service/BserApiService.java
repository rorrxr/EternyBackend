package com.company.eterny.infrastructure.external.bser.service;

import com.company.eterny.infrastructure.external.bser.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * BSER (Eternal Return) API 서비스
 * - 간단하고 안정적인 동기 방식만 사용
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BserApiService {

    private final RestTemplate restTemplate;

    @Value("${bser.api.key}")
    private String apiKey;

    private final String BSER_BASE = "https://open-api.bser.io/v1";

    /**
     * HTTP 헤더 설정
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("x-api-key", apiKey);
        return headers;
    }

    // ==================== 플레이어 정보 ====================

    /**
     * 닉네임으로 유저 검색
     */
    public List<BserUserDto> getUserByNickname(String nickname) {
        log.info("[BSER API] 닉네임으로 유저 검색: {}", nickname);
        
        String url = BSER_BASE + "/user/nickname?query=" +
                UriUtils.encode(nickname, StandardCharsets.UTF_8);
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserUserResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserUserResponse>() {}
            );

            BserUserResponse body = response.getBody();
            List<BserUserDto> users = (body != null && body.getUser() != null)
                    ? body.getUser()
                    : Collections.emptyList();

            log.info("[BSER API] 유저 검색 성공: nickname={}, userCount={}", nickname, users.size());
            return users;

        } catch (HttpClientErrorException.NotFound ex) {
            log.warn("[BSER API] 닉네임 검색 결과 없음: {}", nickname);
            return Collections.emptyList();
        } catch (Exception ex) {
            log.error("[BSER API] 닉네임 검색 실패: nickname={}, error={}", nickname, ex.getMessage());
            throw new RuntimeException("BSER API 호출 실패: " + ex.getMessage(), ex);
        }
    }

    /**
     * userNum으로 유저 정보 조회
     */
    public Optional<BserUserDto> getUserByUserNum(Long userNum) {
        log.info("[BSER API] userNum으로 유저 조회: {}", userNum);
        
        String url = BSER_BASE + "/user/userNum/" + userNum;
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserUserDto> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserUserDto>() {}
            );

            BserUserDto user = response.getBody();
            log.info("[BSER API] 유저 조회 성공: userNum={}, nickname={}", 
                    userNum, user != null ? user.getNickname() : "null");
            return Optional.ofNullable(user);

        } catch (HttpClientErrorException.NotFound ex) {
            log.warn("[BSER API] 유저를 찾을 수 없음: userNum={}", userNum);
            return Optional.empty();
        } catch (Exception ex) {
            log.error("[BSER API] 유저 조회 실패: userNum={}, error={}", userNum, ex.getMessage());
            throw new RuntimeException("유저 정보 조회 실패: " + ex.getMessage(), ex);
        }
    }

    // ==================== 게임 전적 ====================

    /**
     * 게임 전적 조회
     */
    public List<BserGameDto> getGamesByUser(Long userNum) {
        log.info("[BSER API] 게임 전적 조회: userNum={}", userNum);

        String url = BSER_BASE + "/user/games/" + userNum;
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserGamesResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserGamesResponse>() {}
            );

            BserGamesResponse body = response.getBody();
            List<BserGameDto> games = (body != null && body.getUserGames() != null)
                    ? body.getUserGames()
                    : Collections.emptyList();

            log.info("[BSER API] 게임 전적 조회 성공: userNum={}, gameCount={}", userNum, games.size());
            return games;

        } catch (HttpClientErrorException.NotFound ex) {
            log.warn("[BSER API] 게임 전적 없음: userNum={}", userNum);
            return Collections.emptyList();
        } catch (Exception ex) {
            log.error("[BSER API] 게임 전적 조회 실패: userNum={}, error={}", userNum, ex.getMessage());
            throw new RuntimeException("게임 전적 조회 실패: " + ex.getMessage(), ex);
        }
    }

    /**
     * 페이징된 게임 전적 조회
     */
    public List<BserGameDto> getGamesByUser(Long userNum, Integer next) {
        log.info("[BSER API] 페이징 게임 전적 조회: userNum={}, next={}", userNum, next);

        String url = BSER_BASE + "/user/games/" + userNum;
        if (next != null) {
            url += "?next=" + next;
        }
        
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserGamesResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserGamesResponse>() {}
            );

            BserGamesResponse body = response.getBody();
            List<BserGameDto> games = (body != null && body.getUserGames() != null)
                    ? body.getUserGames()
                    : Collections.emptyList();

            log.info("[BSER API] 페이징 게임 전적 조회 성공: userNum={}, gameCount={}", userNum, games.size());
            return games;

        } catch (HttpClientErrorException.NotFound ex) {
            log.warn("[BSER API] 게임 전적 없음: userNum={}, next={}", userNum, next);
            return Collections.emptyList();
        } catch (Exception ex) {
            log.error("[BSER API] 페이징 게임 전적 조회 실패: userNum={}, error={}", userNum, ex.getMessage());
            throw new RuntimeException("게임 전적 조회 실패: " + ex.getMessage(), ex);
        }
    }

    // ==================== 랭크 정보 ====================

    /**
     * 랭크 정보 조회
     */
    public Optional<BserRankDto> getRankByUser(Long userNum, int seasonId, int mode) {
        log.info("[BSER API] 랭크 정보 조회: userNum={}, seasonId={}, mode={}", userNum, seasonId, mode);

        String url = String.format("%s/rank/%d/%d/%d", BSER_BASE, userNum, seasonId, mode);
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserRankResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserRankResponse>() {}
            );

            BserRankResponse body = response.getBody();
            BserRankDto rank = (body != null) ? body.getUserRank() : null;

            log.info("[BSER API] 랭크 정보 조회 성공: userNum={}", userNum);
            return Optional.ofNullable(rank);

        } catch (HttpClientErrorException.NotFound ex) {
            log.warn("[BSER API] 랭크 정보 없음: userNum={}, seasonId={}, mode={}", userNum, seasonId, mode);
            return Optional.empty();
        } catch (Exception ex) {
            log.error("[BSER API] 랭크 정보 조회 실패: userNum={}, error={}", userNum, ex.getMessage());
            throw new RuntimeException("랭크 정보 조회 실패: " + ex.getMessage(), ex);
        }
    }

    /**
     * 플레이어 통계 조회
     */
    public Optional<BserStatsDto> getUserStats(Long userNum, Integer seasonId) {
        log.info("[BSER API] 플레이어 통계 조회: userNum={}, seasonId={}", userNum, seasonId);

        String url = String.format("%s/user/stats/%d/%d", BSER_BASE, userNum, seasonId);
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserStatsDto> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserStatsDto>() {}
            );

            BserStatsDto stats = response.getBody();
            log.info("[BSER API] 플레이어 통계 조회 성공: userNum={}", userNum);
            return Optional.ofNullable(stats);

        } catch (HttpClientErrorException.NotFound ex) {
            log.warn("[BSER API] 플레이어 통계 없음: userNum={}, seasonId={}", userNum, seasonId);
            return Optional.empty();
        } catch (Exception ex) {
            log.error("[BSER API] 플레이어 통계 조회 실패: userNum={}, error={}", userNum, ex.getMessage());
            throw new RuntimeException("플레이어 통계 조회 실패: " + ex.getMessage(), ex);
        }
    }

    // ==================== 랭킹 ====================

    /**
     * 랭킹 정보 조회
     */
    public List<BserRankingDto> getRanking(Integer teamMode, Integer seasonId, Integer next) {
        log.info("[BSER API] 랭킹 조회: teamMode={}, seasonId={}, next={}", teamMode, seasonId, next);

        String url = String.format("%s/rank/%d/%d", BSER_BASE, teamMode, seasonId);
        if (next != null) {
            url += "?next=" + next;
        }
        
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserRankingResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserRankingResponse>() {}
            );

            BserRankingResponse body = response.getBody();
            List<BserRankingDto> rankings = (body != null && body.getTopRanks() != null) 
                    ? body.getTopRanks() 
                    : Collections.emptyList();

            log.info("[BSER API] 랭킹 정보 조회 성공: count={}", rankings.size());
            return rankings;

        } catch (Exception ex) {
            log.error("[BSER API] 랭킹 조회 실패: error={}", ex.getMessage());
            throw new RuntimeException("랭킹 정보 조회 실패: " + ex.getMessage(), ex);
        }
    }

    // ==================== 메타 데이터 ====================

    /**
     * 캐릭터 정보 조회
     */
    public List<BserCharacterDto> getCharacters() {
        log.info("[BSER API] 캐릭터 정보 조회");

        String url = BSER_BASE + "/data/Character";
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserCharacterResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserCharacterResponse>() {}
            );

            BserCharacterResponse body = response.getBody();
            List<BserCharacterDto> characters = (body != null && body.getData() != null) 
                    ? body.getData() 
                    : Collections.emptyList();

            log.info("[BSER API] 캐릭터 정보 조회 성공: count={}", characters.size());
            return characters;

        } catch (Exception ex) {
            log.error("[BSER API] 캐릭터 정보 조회 실패: error={}", ex.getMessage());
            throw new RuntimeException("캐릭터 정보 조회 실패: " + ex.getMessage(), ex);
        }
    }

    /**
     * 무기 정보 조회
     */
    public List<BserWeaponDto> getWeapons() {
        log.info("[BSER API] 무기 정보 조회");

        String url = BSER_BASE + "/data/WeaponType";
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<BserWeaponResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<BserWeaponResponse>() {}
            );

            BserWeaponResponse body = response.getBody();
            List<BserWeaponDto> weapons = (body != null && body.getData() != null) 
                    ? body.getData() 
                    : Collections.emptyList();

            log.info("[BSER API] 무기 정보 조회 성공: count={}", weapons.size());
            return weapons;

        } catch (Exception ex) {
            log.error("[BSER API] 무기 정보 조회 실패: error={}", ex.getMessage());
            throw new RuntimeException("무기 정보 조회 실패: " + ex.getMessage(), ex);
        }
    }

    // ==================== 유틸리티 ====================

    /**
     * API 상태 확인
     */
    public boolean isApiHealthy() {
        log.info("[BSER API] API 상태 확인");

        try {
            String url = BSER_BASE + "/data/Character";
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class
            );

            boolean isHealthy = response.getStatusCode() == HttpStatus.OK;
            log.info("[BSER API] API 상태: {}", isHealthy ? "정상" : "비정상");
            return isHealthy;

        } catch (Exception ex) {
            log.warn("[BSER API] API 상태 비정상: {}", ex.getMessage());
            return false;
        }
    }
}
