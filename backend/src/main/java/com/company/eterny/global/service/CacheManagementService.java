package com.company.eterny.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 캐시 관리 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CacheManagementService {

    private final CacheManager cacheManager;

    /**
     * 특정 캐시 갱신
     * @param cacheName 캐시 이름
     * @return 갱신 성공 여부
     */
    public boolean evictCache(String cacheName) {
        try {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
                log.info("캐시 '{}' 갱신 완료", cacheName);
                return true;
            } else {
                log.warn("캐시 '{}'를 찾을 수 없음", cacheName);
                return false;
            }
        } catch (Exception e) {
            log.error("캐시 '{}' 갱신 실패: {}", cacheName, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 특정 캐시의 특정 키 갱신
     * @param cacheName 캐시 이름
     * @param key 캐시 키
     * @return 갱신 성공 여부
     */
    public boolean evictCacheKey(String cacheName, String key) {
        try {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.evict(key);
                log.info("캐시 '{}' 키 '{}' 갱신 완료", cacheName, key);
                return true;
            } else {
                log.warn("캐시 '{}'를 찾을 수 없음", cacheName);
                return false;
            }
        } catch (Exception e) {
            log.error("캐시 '{}' 키 '{}' 갱신 실패: {}", cacheName, key, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 모든 캐시 갱신
     * @return 갱신된 캐시 개수
     */
    public int evictAllCaches() {
        int count = 0;
        try {
            Collection<String> cacheNames = cacheManager.getCacheNames();
            for (String cacheName : cacheNames) {
                if (evictCache(cacheName)) {
                    count++;
                }
            }
            log.info("전체 캐시 갱신 완료 - {}개 캐시 갱신", count);
        } catch (Exception e) {
            log.error("전체 캐시 갱신 실패: {}", e.getMessage(), e);
        }
        return count;
    }

    /**
     * 캐시 상태 조회
     * @return 캐시 상태 정보
     */
    public Map<String, Object> getCacheStatus() {
        Map<String, Object> status = new HashMap<>();
        
        try {
            Collection<String> cacheNames = cacheManager.getCacheNames();
            status.put("totalCaches", cacheNames.size());
            status.put("cacheNames", cacheNames);
            
            Map<String, Object> cacheDetails = new HashMap<>();
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache != null) {
                    Map<String, Object> detail = new HashMap<>();
                    detail.put("name", cacheName);
                    detail.put("nativeCache", cache.getNativeCache().getClass().getSimpleName());
                    cacheDetails.put(cacheName, detail);
                }
            }
            status.put("cacheDetails", cacheDetails);
            
        } catch (Exception e) {
            log.error("캐시 상태 조회 실패: {}", e.getMessage(), e);
            status.put("error", e.getMessage());
        }
        
        return status;
    }

    /**
     * 플레이어 관련 캐시 갱신
     * @param userNum 유저 번호 (선택적)
     * @return 갱신된 캐시 개수
     */
    public int evictPlayerCaches(Long userNum) {
        int count = 0;
        
        try {
            // 전체 플레이어 캐시 갱신
            if (userNum == null) {
                if (evictCache("playerDetail")) count++;
                if (evictCache("playerRank")) count++;
                if (evictCache("userMatches")) count++;
                if (evictCache("matchDetail")) count++;
            } else {
                // 특정 플레이어 캐시 갱신
                if (evictCacheKey("playerDetail", userNum.toString())) count++;
                if (evictCacheKey("userMatches", userNum.toString())) count++;
                
                // 랭크 캐시는 userNum_season_teamMode 형태이므로 전체 갱신
                if (evictCache("playerRank")) count++;
            }
            
            log.info("플레이어 캐시 갱신 완료 - userNum: {}, {}개 캐시 갱신", userNum, count);
            
        } catch (Exception e) {
            log.error("플레이어 캐시 갱신 실패 - userNum: {}, error: {}", userNum, e.getMessage(), e);
        }
        
        return count;
    }

    /**
     * 리더보드 캐시 갱신
     * @return 갱신 성공 여부
     */
    public boolean evictLeaderboardCache() {
        return evictCache("leaderboard");
    }

    /**
     * 캐시 예열 (Warm-up)
     * @return 예열된 캐시 개수
     */
    public int warmUpCaches() {
        int count = 0;
        
        try {
            // 여기서는 예시로 주요 캐시들을 미리 로드하는 로직을 구현할 수 있습니다.
            // 실제로는 자주 사용되는 데이터들을 미리 캐시에 로드하는 작업
            
            log.info("캐시 예열 완료 - {}개 캐시 예열", count);
            
        } catch (Exception e) {
            log.error("캐시 예열 실패: {}", e.getMessage(), e);
        }
        
        return count;
    }
}
