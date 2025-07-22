package com.company.eterny.domain.game.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @Column(name = "game_seq", unique = true, nullable = false)
    private Long gameSeq;

    @Column(name = "user_num", nullable = false)
    private Long userNum;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "character_code", nullable = false)
    private Integer characterCode;

    @Column(name = "character_name", nullable = false, length = 30)
    private String characterName;

    @Column(name = "skin_code")
    private Integer skinCode;

    @Column(name = "weapon_code")
    private Integer weaponCode;

    @Column(name = "weapon_name", length = 30)
    private String weaponName;

    @Column(name = "game_rank", nullable = false)
    private Integer gameRank;

    @Column(name = "mmr_gain")
    private Integer mmrGain;

    @Column(name = "kills")
    private Integer kills;

    @Column(name = "assists")
    private Integer assists;

    @Column(name = "hunt_level")
    private Integer huntLevel;

    @Column(name = "equipment_level")
    private Integer equipmentLevel;

    @Column(name = "play_time")
    private Integer playTime;

    @Column(name = "team_mode", nullable = false)
    private Integer teamMode;

    @Column(name = "team_members_count")
    private Integer teamMembersCount;

    @Column(name = "server_name", length = 20)
    private String serverName;

    @Column(name = "season_id")
    private Integer seasonId;

    @Column(name = "game_started_at", nullable = false)
    private LocalDateTime gameStartedAt;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Game(Long gameSeq, Long userNum, String nickname, Integer characterCode,
                String characterName, Integer skinCode, Integer weaponCode,
                String weaponName, Integer gameRank, Integer mmrGain,
                Integer kills, Integer assists, Integer huntLevel,
                Integer equipmentLevel, Integer playTime, Integer teamMode,
                Integer teamMembersCount, String serverName, Integer seasonId,
                LocalDateTime gameStartedAt) {
        this.gameSeq = gameSeq;
        this.userNum = userNum;
        this.nickname = nickname;
        this.characterCode = characterCode;
        this.characterName = characterName;
        this.skinCode = skinCode;
        this.weaponCode = weaponCode;
        this.weaponName = weaponName;
        this.gameRank = gameRank;
        this.mmrGain = mmrGain;
        this.kills = kills;
        this.assists = assists;
        this.huntLevel = huntLevel;
        this.equipmentLevel = equipmentLevel;
        this.playTime = playTime;
        this.teamMode = teamMode;
        this.teamMembersCount = teamMembersCount;
        this.serverName = serverName;
        this.seasonId = seasonId;
        this.gameStartedAt = gameStartedAt;
    }

    // 비즈니스 로직 메서드
    public boolean isWin() {
        return gameRank != null && gameRank == 1;
    }

    public boolean isTop3() {
        return gameRank != null && gameRank <= 3;
    }

    public boolean isPositiveMMR() {
        return mmrGain != null && mmrGain > 0;
    }

    public String getTeamModeText() {
        return switch (teamMode) {
            case 1 -> "솔로";
            case 2 -> "듀오";
            case 3 -> "스쿼드";
            default -> "알 수 없음";
        };
    }

    public String getPerformanceGrade() {
        if (gameRank == null) return "F";

        return switch (gameRank) {
            case 1 -> "S+";
            case 2, 3 -> "S";
            case 4, 5 -> "A+";
            case 6, 7, 8 -> "A";
            case 9, 10, 11 -> "B+";
            case 12, 13, 14 -> "B";
            case 15, 16, 17 -> "C+";
            case 18, 19, 20 -> "C";
            default -> "D";
        };
    }

    // 게임 시간을 분:초 형태로 반환
    public String getFormattedPlayTime() {
        if (playTime == null) return "00:00";

        int minutes = playTime / 60;
        int seconds = playTime % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    // KDA 계산
    public double getKDA() {
        if (kills == null) kills = 0;
        if (assists == null) assists = 0;

        return kills + (assists * 0.5);
    }
}