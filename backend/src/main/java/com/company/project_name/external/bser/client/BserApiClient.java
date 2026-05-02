package com.company.project_name.external.bser.client;

import com.company.project_name.external.bser.dto.*;
import com.company.project_name.external.bser.exception.BserApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BserApiClient {

    @Qualifier("bserRestTemplate")
    private final RestTemplate bserRestTemplate;

    @Qualifier("bserBaseUrl")
    private final String bserBaseUrl;

    public BserUserNicknameResponseDto getUserByNickname(String nickname) {
        String url = bserBaseUrl + "/v1/user/nickname/" + nickname;
        return get(url, BserUserNicknameResponseDto.class);
    }

    public BserUserStatsResponseDto getUserStats(long userNum, int seasonId) {
        String url = bserBaseUrl + "/v1/user/stats/" + userNum + "/" + seasonId;
        return get(url, BserUserStatsResponseDto.class);
    }

    public BserUserGamesResponseDto getUserGames(long userNum) {
        String url = bserBaseUrl + "/v1/user/games/" + userNum;
        return get(url, BserUserGamesResponseDto.class);
    }

    public BserGameDetailResponseDto getGameDetail(long gameId) {
        String url = bserBaseUrl + "/v1/games/" + gameId;
        return get(url, BserGameDetailResponseDto.class);
    }

    public BserRankResponseDto getUserRank(long userNum, int seasonId, int matchingTeamMode) {
        String url = bserBaseUrl + "/v1/rank/" + userNum + "/" + seasonId + "/" + matchingTeamMode;
        return get(url, BserRankResponseDto.class);
    }

    public BserLeaderboardResponseDto getLeaderboard(int seasonId, int matchingTeamMode, int count) {
        String url = bserBaseUrl + "/v2/rank/" + seasonId + "/" + matchingTeamMode + "?count=" + count;
        return get(url, BserLeaderboardResponseDto.class);
    }

    private <T> T get(String url, Class<T> responseType) {
        try {
            T result = bserRestTemplate.getForObject(url, responseType);
            if (result == null) {
                throw new BserApiException(502, "BSER API 응답이 없습니다: " + url);
            }
            return result;
        } catch (HttpClientErrorException e) {
            throw new BserApiException(e.getStatusCode().value(),
                    "BSER API 오류 [" + e.getStatusCode().value() + "]: " + e.getMessage());
        } catch (RestClientException e) {
            throw new BserApiException(503, "BSER API 연결 실패: " + e.getMessage());
        }
    }
}
