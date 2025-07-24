package com.company.eterny.domain.player.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "players")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(name = "user_num", unique = true, nullable = false)
    private Long userNum;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "mmr")
    private Integer mmr;

    @Column(name = "rank_tier")
    private Integer rank;

    @Column(name = "rank_tier_name", length = 20)
    private String rankName;

    @Column(name = "total_games")
    private Integer totalGames;

    @Column(name = "total_wins")
    private Integer totalWins;

    @Column(name = "win_rate")
    private Double winRate;

    @Column(name = "avg_rank")
    private Double avgRank;

    @Column(name = "top3_rate")
    private Double top3Rate;

    @Column(name = "avg_kills")
    private Double avgKills;

    @Column(name = "avg_assists")
    private Double avgAssists;

    @Column(name = "most_character_code")
    private Integer mostCharacterCode;

    @Column(name = "most_character_name", length = 30)
    private String mostCharacterName;

    @Column(name = "last_game_date")
    private LocalDateTime lastGameDate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Player(Long userNum, String nickname, Integer mmr, Integer rank,
                  String rankName, Integer totalGames, Integer totalWins,
                  Double winRate, Double avgRank, Double top3Rate,
                  Double avgKills, Double avgAssists, Integer mostCharacterCode,
                  String mostCharacterName, LocalDateTime lastGameDate) {
        this.userNum = userNum;
        this.nickname = nickname;
        this.mmr = mmr;
        this.rank = rank;
        this.rankName = rankName;
        this.totalGames = totalGames;
        this.totalWins = totalWins;
        this.winRate = winRate;
        this.avgRank = avgRank;
        this.top3Rate = top3Rate;
        this.avgKills = avgKills;
        this.avgAssists = avgAssists;
        this.mostCharacterCode = mostCharacterCode;
        this.mostCharacterName = mostCharacterName;
        this.lastGameDate = lastGameDate;
    }

    // 비즈니스 로직 메서드
    public void updateStats(Integer mmr, Integer rank, String rankName,
                            Integer totalGames, Integer totalWins, Double winRate,
                            Double avgRank, Double top3Rate, Double avgKills,
                            Double avgAssists, LocalDateTime lastGameDate) {
        this.mmr = mmr;
        this.rank = rank;
        this.rankName = rankName;
        this.totalGames = totalGames;
        this.totalWins = totalWins;
        this.winRate = winRate;
        this.avgRank = avgRank;
        this.top3Rate = top3Rate;
        this.avgKills = avgKills;
        this.avgAssists = avgAssists;
        this.lastGameDate = lastGameDate;
    }

    public void updateMostCharacter(Integer characterCode, String characterName) {
        this.mostCharacterCode = characterCode;
        this.mostCharacterName = characterName;
    }

    // 유틸리티 메서드
    public boolean isActive() {
        return lastGameDate != null &&
                lastGameDate.isAfter(LocalDateTime.now().minusDays(30));
    }

    public String getTierLevel() {
        if (rank == null) return "언랭크";

        return switch (rank) {
            case 0, 1, 2, 3 -> "아이언";
            case 4, 5, 6, 7 -> "브론즈";
            case 8, 9, 10, 11 -> "실버";
            case 12, 13, 14, 15 -> "골드";
            case 16, 17, 18, 19 -> "플래티넘";
            case 20, 21, 22, 23 -> "다이아몬드";
            case 24, 25, 26 -> "미스릴";
            case 27 -> "이모탈";
            default -> "언랭크";
        };
    }
}