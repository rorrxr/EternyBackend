package com.company.eterny.domain.player.dto;

import com.company.eterny.domain.player.entity.Player;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class PlayerDto {

    /**
     * 플레이어 검색 요청 DTO
     */
    @Getter
    @Builder
    public static class SearchRequest {
        private String nickname;
        private Integer page;
        private Integer size;
        private String sortBy; // mmr, winRate, totalGames, lastGameDate
        private String sortDirection; // asc, desc

        public int getPageOrDefault() {
            return page != null && page >= 0 ? page : 0;
        }

        public int getSizeOrDefault() {
            return size != null && size > 0 && size <= 100 ? size : 20;
        }

        public String getSortByOrDefault() {
            return sortBy != null ? sortBy : "mmr";
        }

        public String getSortDirectionOrDefault() {
            return "asc".equalsIgnoreCase(sortDirection) ? "asc" : "desc";
        }
    }

    /**
     * 플레이어 기본 정보 응답 DTO
     */
    @Getter
    @Builder
    public static class Response {
        private Long id;
        private Long userNum;
        private String nickname;
        private Integer mmr;
        private Integer rank;
        private String rankName;
        private String tierLevel;
        private Integer totalGames;
        private Integer totalWins;
        private Double winRate;
        private Double avgRank;
        private Double top3Rate;
        private Double avgKills;
        private Double avgAssists;
        private String mostCharacterName;
        private LocalDateTime lastGameDate;
        private boolean isActive;
        private LocalDateTime updatedAt;

        public static Response from(Player player) {
            return Response.builder()
                    .id(player.getId())
                    .userNum(player.getUserNum())
                    .nickname(player.getNickname())
                    .mmr(player.getMmr())
                    .rank(player.getRank())
                    .rankName(player.getRankName())
                    .tierLevel(player.getTierLevel())
                    .totalGames(player.getTotalGames())
                    .totalWins(player.getTotalWins())
                    .winRate(player.getWinRate())
                    .avgRank(player.getAvgRank())
                    .top3Rate(player.getTop3Rate())
                    .avgKills(player.getAvgKills())
                    .avgAssists(player.getAvgAssists())
                    .mostCharacterName(player.getMostCharacterName())
                    .lastGameDate(player.getLastGameDate())
                    .isActive(player.isActive())
                    .updatedAt(player.getUpdatedAt())
                    .build();
        }
    }

    /**
     * 플레이어 간단 정보 DTO (목록용)
     */
    @Getter
    @Builder
    public static class Summary {
        private Long userNum;
        private String nickname;
        private Integer mmr;
        private String tierLevel;
        private Double winRate;
        private Integer totalGames;
        private String mostCharacterName;
        private boolean isActive;

        public static Summary from(Player player) {
            return Summary.builder()
                    .userNum(player.getUserNum())
                    .nickname(player.getNickname())
                    .mmr(player.getMmr())
                    .tierLevel(player.getTierLevel())
                    .winRate(player.getWinRate())
                    .totalGames(player.getTotalGames())
                    .mostCharacterName(player.getMostCharacterName())
                    .isActive(player.isActive())
                    .build();
        }
    }

    /**
     * 플레이어 상세 정보 DTO (전적 포함)
     */
    @Getter
    @Builder
    public static class DetailResponse {
        private Response playerInfo;
        private PlayerStats stats;
        private List<String> recentCharacters;
        private List<String> recentWeapons;

        @Getter
        @Builder
        public static class PlayerStats {
            private Integer totalGames;
            private Integer totalWins;
            private Integer totalTop3;
            private Double winRate;
            private Double top3Rate;
            private Double avgRank;
            private Double avgKills;
            private Double avgAssists;
            private Double avgKDA;
            private Integer totalMmrGain;
            private String performanceGrade;
        }
    }

    /**
     * 플레이어 랭킹 DTO
     */
    @Getter
    @Builder
    public static class RankingResponse {
        private Integer ranking;
        private Long userNum;
        private String nickname;
        private Integer mmr;
        private String tierLevel;
        private Double winRate;
        private Integer totalGames;
        private String mostCharacterName;

        public static RankingResponse from(Player player, Integer ranking) {
            return RankingResponse.builder()
                    .ranking(ranking)
                    .userNum(player.getUserNum())
                    .nickname(player.getNickname())
                    .mmr(player.getMmr())
                    .tierLevel(player.getTierLevel())
                    .winRate(player.getWinRate())
                    .totalGames(player.getTotalGames())
                    .mostCharacterName(player.getMostCharacterName())
                    .build();
        }
    }

    /**
     * 플레이어 업데이트 요청 DTO
     */
    @Getter
    @Builder
    public static class UpdateRequest {
        private Long userNum;
        private String nickname;
        private Integer mmr;
        private Integer rank;
        private String rankName;
        private Integer totalGames;
        private Integer totalWins;
        private Double winRate;
        private Double avgRank;
        private Double top3Rate;
        private Double avgKills;
        private Double avgAssists;
        private Integer mostCharacterCode;
        private String mostCharacterName;
        private LocalDateTime lastGameDate;
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