package com.company.eterny.domain.game.dto;

import com.company.eterny.domain.game.entity.Game;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class GameDto {

    /**
     * 게임 기록 검색 요청 DTO
     */
    @Getter
    @Builder
    public static class SearchRequest {
        private Long userNum;
        private Integer characterCode;
        private Integer teamMode;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer seasonId;
        private Integer page;
        private Integer size;
        private String sortBy; // gameStartedAt, gameRank, mmrGain
        private String sortDirection; // asc, desc

        public int getPageOrDefault() {
            return page != null && page >= 0 ? page : 0;
        }

        public int getSizeOrDefault() {
            return size != null && size > 0 && size <= 100 ? size : 20;
        }

        public String getSortByOrDefault() {
            return sortBy != null ? sortBy : "gameStartedAt";
        }

        public String getSortDirectionOrDefault() {
            return "asc".equalsIgnoreCase(sortDirection) ? "asc" : "desc";
        }
    }

    /**
     * 게임 기록 응답 DTO
     */
    @Getter
    @Builder
    public static class Response {
        private Long id;
        private Long gameSeq;
        private Long userNum;
        private String nickname;
        private String characterName;
        private String weaponName;
        private Integer gameRank;
        private Integer mmrGain;
        private Integer kills;
        private Integer assists;
        private Double kda;
        private Integer huntLevel;
        private Integer equipmentLevel;
        private String playTime;
        private String teamMode;
        private String serverName;
        private Integer seasonId;
        private LocalDateTime gameStartedAt;
        private String performanceGrade;
        private boolean isWin;
        private boolean isTop3;
        private boolean isPositiveMMR;

        public static Response from(Game game) {
            return Response.builder()
                    .id(game.getId())
                    .gameSeq(game.getGameSeq())
                    .userNum(game.getUserNum())
                    .nickname(game.getNickname())
                    .characterName(game.getCharacterName())
                    .weaponName(game.getWeaponName())
                    .gameRank(game.getGameRank())
                    .mmrGain(game.getMmrGain())
                    .kills(game.getKills())
                    .assists(game.getAssists())
                    .kda(game.getKDA())
                    .huntLevel(game.getHuntLevel())
                    .equipmentLevel(game.getEquipmentLevel())
                    .playTime(game.getFormattedPlayTime())
                    .teamMode(game.getTeamModeText())
                    .serverName(game.getServerName())
                    .seasonId(game.getSeasonId())
                    .gameStartedAt(game.getGameStartedAt())
                    .performanceGrade(game.getPerformanceGrade())
                    .isWin(game.isWin())
                    .isTop3(game.isTop3())
                    .isPositiveMMR(game.isPositiveMMR())
                    .build();
        }
    }

    /**
     * 게임 기록 간단 정보 DTO (목록용)
     */
    @Getter
    @Builder
    public static class Summary {
        private Long gameSeq;
        private String characterName;
        private Integer gameRank;
        private Integer mmrGain;
        private Integer kills;
        private Integer assists;
        private String teamMode;
        private LocalDateTime gameStartedAt;
        private String performanceGrade;
        private boolean isWin;

        public static Summary from(Game game) {
            return Summary.builder()
                    .gameSeq(game.getGameSeq())
                    .characterName(game.getCharacterName())
                    .gameRank(game.getGameRank())
                    .mmrGain(game.getMmrGain())
                    .kills(game.getKills())
                    .assists(game.getAssists())
                    .teamMode(game.getTeamModeText())
                    .gameStartedAt(game.getGameStartedAt())
                    .performanceGrade(game.getPerformanceGrade())
                    .isWin(game.isWin())
                    .build();
        }
    }

    /**
     * 게임 통계 DTO
     */
    @Getter
    @Builder
    public static class StatsResponse {
        private Long totalGames;
        private Long totalWins;
        private Long totalTop3;
        private Double winRate;
        private Double top3Rate;
        private Double avgRank;
        private Double avgKills;
        private Double avgAssists;
        private Double avgKDA;
        private Long totalMmrGain;
        private List<CharacterStats> characterStats;
        private List<WeaponStats> weaponStats;
        private RecentPerformance recentPerformance;

        @Getter
        @Builder
        public static class CharacterStats {
            private Integer characterCode;
            private String characterName;
            private Long gameCount;
            private Long wins;
            private Double winRate;
            private Double avgRank;
            private Double avgKills;
            private Double avgAssists;
        }

        @Getter
        @Builder
        public static class WeaponStats {
            private Integer weaponCode;
            private String weaponName;
            private Long gameCount;
            private Long wins;
            private Double winRate;
            private Double avgRank;
        }

        @Getter
        @Builder
        public static class RecentPerformance {
            private Integer recentGames; // 최근 N게임
            private Long wins;
            private Double winRate;
            private Double avgRank;
            private Double avgMMRGain;
            private String trend; // "상승", "하락", "유지"
        }
    }

    /**
     * 게임 생성 요청 DTO
     */
    @Getter
    @Builder
    public static class CreateRequest {
        private Long gameSeq;
        private Long userNum;
        private String nickname;
        private Integer characterCode;
        private String characterName;
        private Integer skinCode;
        private Integer weaponCode;
        private String weaponName;
        private Integer gameRank;
        private Integer mmrGain;
        private Integer kills;
        private Integer assists;
        private Integer huntLevel;
        private Integer equipmentLevel;
        private Integer playTime;
        private Integer teamMode;
        private Integer teamMembersCount;
        private String serverName;
        private Integer seasonId;
        private LocalDateTime gameStartedAt;
    }

    /**
     * 게임 업데이트 요청 DTO
     */
    @Getter
    @Builder
    public static class UpdateRequest {
        private Long gameSeq;
        private Integer gameRank;
        private Integer mmrGain;
        private Integer kills;
        private Integer assists;
        private Integer huntLevel;
        private Integer equipmentLevel;
        private Integer playTime;
    }

    /**
     * 게임 기록 필터 DTO
     */
    @Getter
    @Builder
    public static class FilterRequest {
        private List<Integer> characterCodes;
        private List<Integer> weaponCodes;
        private List<Integer> teamModes;
        private Integer minRank;
        private Integer maxRank;
        private Integer minMMRGain;
        private Integer maxMMRGain;
        private Integer minKills;
        private Integer maxKills;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<Integer> seasonIds;
        private Boolean winsOnly;
        private Boolean top3Only;
        private String sortBy;
        private String sortDirection;
        private Integer page;
        private Integer size;
    }

    /**
     * 일일 통계 DTO
     */
    @Getter
    @Builder
    public static class DailyStats {
        private LocalDateTime date;
        private Integer gamesPlayed;
        private Integer wins;
        private Integer top3;
        private Double winRate;
        private Double top3Rate;
        private Double avgRank;
        private Integer totalMMRGain;
        private List<String> charactersPlayed;
        private String bestGame; // 가장 좋았던 게임의 간단한 설명
    }
    
    /**
     * 페이지네이션 응답 DTO
     */
    @Getter
    @Builder
    public static class PageResponse<T> {
        private List<T> content;
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
        private Integer size;
        private boolean hasNext;
        private boolean hasPrevious;
        private boolean isFirst;
        private boolean isLast;
        
        public static <T> PageResponse<T> of(List<T> content, Integer currentPage, 
                                            Integer totalPages, Long totalElements, 
                                            Integer size) {
            return PageResponse.<T>builder()
                    .content(content)
                    .currentPage(currentPage)
                    .totalPages(totalPages)
                    .totalElements(totalElements)
                    .size(size)
                    .hasNext(currentPage < totalPages - 1)
                    .hasPrevious(currentPage > 0)
                    .isFirst(currentPage == 0)
                    .isLast(currentPage == totalPages - 1)
                    .build();
        }
    }
}