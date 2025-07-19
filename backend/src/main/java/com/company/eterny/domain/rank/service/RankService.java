package com.company.eterny.domain.rank.service;

import com.company.eterny.domain.rank.dto.RankPredictionDto;
import com.company.eterny.infrastructure.external.bser.dto.BserRankDto;
import com.company.eterny.infrastructure.external.bser.dto.BserTopRankDto;
import com.company.eterny.infrastructure.external.bser.service.BserExternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 랭크 관련 비즈니스 로직을 처리하는 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RankService {

    private final BserExternalService bserExternalService;

    /**
     * 플레이어 랭크 정보 조회
     * @param userNum 유저 번호
     * @param season 시즌
     * @param teamMode 팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)
     * @return 랭크 정보
     */
    @Cacheable(value = "playerRank", key = "#userNum + '_' + #season + '_' + #teamMode")
    public BserRankDto getPlayerRank(Long userNum, Integer season, Integer teamMode) {
        log.info("플레이어 랭크 정보 조회 - userNum: {}, season: {}, teamMode: {}", userNum, season, teamMode);
        
        BserRankDto rankInfo = bserExternalService.getUserRank(userNum, season, teamMode);
        
        if (rankInfo == null) {
            log.warn("랭크 정보를 찾을 수 없습니다 - userNum: {}", userNum);
        }
        
        return rankInfo;
    }

    /**
     * 리더보드 조회 (페이징 처리)
     * @param season 시즌
     * @param teamMode 팀 모드
     * @param pageable 페이징 정보
     * @return 페이징된 리더보드
     */
    @Cacheable(value = "leaderboard", key = "#season + '_' + #teamMode + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<BserTopRankDto> getLeaderboard(Integer season, Integer teamMode, Pageable pageable) {
        log.info("리더보드 조회 - season: {}, teamMode: {}, page: {}, size: {}", 
                season, teamMode, pageable.getPageNumber(), pageable.getPageSize());
        
        List<BserTopRankDto> allRanks = bserExternalService.getLeaderboard(season, teamMode);
        
        // 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allRanks.size());
        
        List<BserTopRankDto> pagedRanks = (start < allRanks.size()) 
                ? allRanks.subList(start, end) 
                : List.of();
        
        return new PageImpl<>(pagedRanks, pageable, allRanks.size());
    }

    /**
     * 승리 수 기반 랭크 예측
     * @param userNum 유저 번호
     * @param season 시즌
     * @param teamMode 팀 모드
     * @param targetWins 목표 승리 수
     * @return 예측된 랭크 정보
     */
    public RankPredictionDto predictRank(Long userNum, Integer season, Integer teamMode, Integer targetWins) {
        log.info("랭크 예측 - userNum: {}, targetWins: {}", userNum, targetWins);
        
        // 현재 랭크 정보 조회
        BserRankDto currentRank = getPlayerRank(userNum, season, teamMode);
        
        if (currentRank == null) {
            throw new RuntimeException("현재 랭크 정보를 찾을 수 없습니다.");
        }
        
        // 간단한 예측 로직 (실제로는 더 복잡한 알고리즘 필요)
        return calculateRankPrediction(currentRank, targetWins);
    }

    /**
     * 랭크 예측 계산 로직
     */
    private RankPredictionDto calculateRankPrediction(BserRankDto currentRank, Integer targetWins) {
        // 현재 승리 수와 목표 승리 수의 차이
        int additionalWins = targetWins - (currentRank.getWins() != null ? currentRank.getWins() : 0);
        
        if (additionalWins <= 0) {
            return RankPredictionDto.builder()
                    .currentRank(currentRank)
                    .predictedTier(currentRank.getTierType())
                    .predictedDivision(currentRank.getDivision())
                    .additionalWinsNeeded(0)
                    .message("이미 목표 승리 수를 달성했습니다.")
                    .build();
        }
        
        // 간단한 예측 로직 (실제로는 MMR, 승률 등을 고려해야 함)
        String predictedTier = calculatePredictedTier(currentRank, additionalWins);
        Integer predictedDivision = calculatePredictedDivision(currentRank, additionalWins);
        
        return RankPredictionDto.builder()
                .currentRank(currentRank)
                .predictedTier(predictedTier)
                .predictedDivision(predictedDivision)
                .additionalWinsNeeded(additionalWins)
                .message(String.format("%d승 더 하면 %s %d단계에 도달할 가능성이 높습니다.", 
                        additionalWins, predictedTier, predictedDivision))
                .build();
    }

    private String calculatePredictedTier(BserRankDto currentRank, int additionalWins) {
        // 현재 티어를 기반으로 예측 (예시 로직)
        if (currentRank.getTierType() == null) return "BRONZE";
        
        // 추가 승리에 따른 티어 상승 예측 (간단한 예시)
        String[] tiers = {"BRONZE", "SILVER", "GOLD", "PLATINUM", "DIAMOND", "TITAN", "IMMORTAL"};
        int currentTierIndex = getTierIndex(currentRank.getTierType());
        
        // 10승당 1티어 상승 (예시)
        int tierUpgrade = additionalWins / 10;
        int newTierIndex = Math.min(currentTierIndex + tierUpgrade, tiers.length - 1);
        
        return tiers[newTierIndex];
    }

    private Integer calculatePredictedDivision(BserRankDto currentRank, int additionalWins) {
        // 현재 단계를 기반으로 예측
        Integer currentDivision = currentRank.getDivision() != null ? currentRank.getDivision() : 1;
        
        // 3승당 1단계 상승 (예시)
        int divisionUpgrade = additionalWins / 3;
        return Math.min(currentDivision + divisionUpgrade, 5); // 최대 5단계
    }

    private int getTierIndex(String tier) {
        String[] tiers = {"BRONZE", "SILVER", "GOLD", "PLATINUM", "DIAMOND", "TITAN", "IMMORTAL"};
        for (int i = 0; i < tiers.length; i++) {
            if (tiers[i].equals(tier)) {
                return i;
            }
        }
        return 0; // 기본값: BRONZE
    }
}
