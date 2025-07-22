package com.company.eterny.domain.match.service;

import com.company.eterny.domain.match.dto.MatchHistoryFilterDto;
import com.company.eterny.domain.match.dto.MatchStatsDto;
import com.company.eterny.infrastructure.external.bser.dto.BserApiDto;
import com.company.eterny.infrastructure.external.bser.service.BserApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 매치 관련 비즈니스 로직을 처리하는 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final BserApiService bserApiService;

    /**
     * 유저의 최근 매치 기록 조회
     * @param userNum 유저 번호
     * @return 매치 기록 목록
     */
    public List<BserApiDto.GameResponse> getUserMatches(Long userNum) {
        log.info("유저 매치 기록 조회 - userNum: {}", userNum);
        
        // TODO: MVP에서는 임시로 빈 리스트 반환 - 추후 BserGameDto -> BserApiDto.GameResponse 변환 구현
        List<BserApiDto.GameResponse> matches = List.of();
        
        if (matches.isEmpty()) {
            log.warn("매치 기록을 찾을 수 없습니다 - userNum: {}", userNum);
        }
        
        return matches;
    }

    /**
     * 필터 조건에 따른 매치 히스토리 조회
     * @param filter 필터 조건
     * @param pageable 페이징 정보
     * @return 페이징된 매치 기록
     */
    public Page<BserApiDto.GameResponse> getMatchHistory(MatchHistoryFilterDto filter, Pageable pageable) {
        log.info("매치 히스토리 조회 - filter: {}, page: {}, size: {}", 
                filter, pageable.getPageNumber(), pageable.getPageSize());
        
        // 기본 매치 목록 조회
        List<BserApiDto.GameResponse> allMatches = getUserMatches(filter.getUserNum());
        
        // 필터 적용
        List<BserApiDto.GameResponse> filteredMatches = applyFilters(allMatches, filter);
        
        // 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredMatches.size());
        
        List<BserApiDto.GameResponse> pagedMatches = (start < filteredMatches.size()) 
                ? filteredMatches.subList(start, end) 
                : List.of();
        
        return new PageImpl<>(pagedMatches, pageable, filteredMatches.size());
    }

    /**
     * 특정 매치 상세 정보 조회
     * @param matchId 매치 ID
     * @param userNum 조회하는 유저 번호 (권한 확인용)
     * @return 매치 상세 정보
     */
    public BserApiDto.GameResponse getMatchDetail(Long matchId, Long userNum) {
        log.info("매치 상세 정보 조회 - matchId: {}, userNum: {}", matchId, userNum);
        
        // TODO: MVP에서는 임시로 빈 리스트 사용 - 추후 구현
        List<BserApiDto.GameResponse> userGames = List.of();
        
        BserApiDto.GameResponse matchDetail = userGames.stream()
                .filter(game -> game.getGameId().equals(matchId))
                .findFirst()
                .orElse(null);
        
        if (matchDetail == null) {
            throw new RuntimeException("매치 정보를 찾을 수 없습니다.");
        }
        
        // 유저가 해당 매치에 참여했는지 확인 (보안)
        if (!userNum.equals(matchDetail.getUserNum())) {
            log.warn("유저가 참여하지 않은 매치에 접근 시도 - matchId: {}, userNum: {}, matchUserNum: {}", 
                    matchId, userNum, matchDetail.getUserNum());
            throw new RuntimeException("해당 매치에 참여하지 않은 사용자입니다.");
        }
        
        return matchDetail;
    }

    /**
     * 필터 조건 적용
     */
    private List<BserApiDto.GameResponse> applyFilters(List<BserApiDto.GameResponse> matches, MatchHistoryFilterDto filter) {
        return matches.stream()
                .filter(match -> filter.getSeason() == null || filter.getSeason().equals(match.getSeasonId()))
                .filter(match -> filter.getGameMode() == null || 
                        ("normal".equals(filter.getGameMode()) && match.getMatchingMode() != null && match.getMatchingMode() == 2) ||
                        ("rank".equals(filter.getGameMode()) && match.getMatchingMode() != null && match.getMatchingMode() == 3))
                .filter(match -> filter.getCharacterId() == null || filter.getCharacterId().equals(match.getCharacterNum()))
                .filter(match -> filter.getTeamMode() == null || filter.getTeamMode().equals(match.getMatchingTeamMode()))
                .filter(match -> filter.getMinRank() == null || match.getGameRank() == null || match.getGameRank() >= filter.getMinRank())
                .filter(match -> filter.getMaxRank() == null || match.getGameRank() == null || match.getGameRank() <= filter.getMaxRank())
                .collect(Collectors.toList());
    }

    /**
     * 매치 통계 조회
     * @param userNum 유저 번호
     * @param season 시즌 (선택적)
     * @return 매치 통계 정보
     */
    public MatchStatsDto getMatchStats(Long userNum, Integer season) {
        log.info("매치 통계 조회 - userNum: {}, season: {}", userNum, season);
        
        List<BserApiDto.GameResponse> matches = getUserMatches(userNum);
        
        // 시즌 필터 적용
        if (season != null) {
            matches = matches.stream()
                    .filter(match -> season.equals(match.getSeasonId()))
                    .collect(Collectors.toList());
        }
        
        return calculateMatchStats(matches);
    }

    /**
     * 매치 통계 계산
     */
    private MatchStatsDto calculateMatchStats(List<BserApiDto.GameResponse> matches) {
        if (matches.isEmpty()) {
            return MatchStatsDto.builder()
                    .totalMatches(0)
                    .wins(0)
                    .winRate(0.0)
                    .averageRank(0.0)
                    .bestRank(null)
                    .averageKills(0.0)
                    .top3Count(0)
                    .top3Rate(0.0)
                    .build();
        }
        
        int totalMatches = matches.size();
        
        // 승리 수 계산 (1등)
        long wins = matches.stream()
                .filter(match -> match.getGameRank() != null && match.getGameRank() == 1)
                .count();
        
        double winRate = (double) wins / totalMatches * 100;
        
        // 평균 순위 계산
        double averageRank = matches.stream()
                .filter(match -> match.getGameRank() != null)
                .mapToInt(BserApiDto.GameResponse::getGameRank)
                .average()
                .orElse(0.0);
        
        // 최고 순위 (가장 낮은 숫자가 최고)
        Integer bestRank = matches.stream()
                .filter(match -> match.getGameRank() != null)
                .mapToInt(BserApiDto.GameResponse::getGameRank)
                .min()
                .orElse(0);
        
        // 평균 킬 수
        double averageKills = matches.stream()
                .filter(match -> match.getPlayerKill() != null)
                .mapToInt(BserApiDto.GameResponse::getPlayerKill)
                .average()
                .orElse(0.0);
        
        // Top 3 달성 횟수
        long top3Count = matches.stream()
                .filter(match -> match.getGameRank() != null && match.getGameRank() <= 3)
                .count();
        
        double top3Rate = (double) top3Count / totalMatches * 100;
        
        // 평균 생존 시간 (duration 사용)
        Integer averageSurvivalTime = (int) matches.stream()
                .filter(match -> match.getDuration() != null)
                .mapToInt(BserApiDto.GameResponse::getDuration)
                .average()
                .orElse(0.0);
        
        return MatchStatsDto.builder()
                .totalMatches(totalMatches)
                .wins((int) wins)
                .winRate(winRate)
                .averageRank(averageRank)
                .bestRank(bestRank)
                .averageKills(averageKills)
                .averageSurvivalTime(averageSurvivalTime)
                .top3Count((int) top3Count)
                .top3Rate(top3Rate)
                .build();
    }
}
