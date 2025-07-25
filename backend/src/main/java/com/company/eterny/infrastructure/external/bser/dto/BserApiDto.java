package com.company.eterny.infrastructure.external.bser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class BserApiDto {

    /**
     * 사용자 정보 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class UserResponse {
        @JsonProperty("userNum")
        private Long userNum;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("mmr")
        private Integer mmr;

        @JsonProperty("rank")
        private Integer rank;

        @JsonProperty("rankSize")
        private Integer rankSize;

        @JsonProperty("totalGames")
        private Integer totalGames;

        @JsonProperty("totalWins")
        private Integer totalWins;

        @JsonProperty("totalTeamKills")
        private Integer totalTeamKills;

        @JsonProperty("totalDeaths")
        private Integer totalDeaths;

        @JsonProperty("escapeCount")
        private Integer escapeCount;

        @JsonProperty("rankPercent")
        private Double rankPercent;

        @JsonProperty("averageRank")
        private Double averageRank;

        @JsonProperty("averageKills")
        private Double averageKills;

        @JsonProperty("averageAssistants")
        private Double averageAssistants;

        @JsonProperty("top1")
        private Integer top1;

        @JsonProperty("top2")
        private Integer top2;

        @JsonProperty("top3")
        private Integer top3;

        @JsonProperty("top5")
        private Integer top5;

        @JsonProperty("top7")
        private Integer top7;

        @JsonProperty("mostCharacter")
        private Integer mostCharacter;

        @JsonProperty("mostCharacterName")
        private String mostCharacterName;

        @JsonProperty("characterStats")
        private List<CharacterStats> characterStats;

        // Getter 메서드들 (Lombok @Data가 작동하지 않을 경우를 위해)
        public Long getUserNum() { return userNum; }
        public String getNickname() { return nickname; }
        public Integer getMmr() { return mmr; }
        public Integer getRank() { return rank; }
        public Integer getTotalGames() { return totalGames; }
        public Integer getTotalWins() { return totalWins; }

        // 계산된 필드들
        public Double getWinRate() {
            if (totalGames == null || totalGames == 0) return 0.0;
            return (totalWins != null ? totalWins.doubleValue() : 0.0) / totalGames.doubleValue() * 100.0;
        }

        public Double getTop3Rate() {
            if (totalGames == null || totalGames == 0) return 0.0;
            int top3Count = (top1 != null ? top1 : 0) + (top2 != null ? top2 : 0) + (top3 != null ? top3 : 0);
            return top3Count / totalGames.doubleValue() * 100.0;
        }

        @Data
        @NoArgsConstructor
        public static class CharacterStats {
            @JsonProperty("characterCode")
            private Integer characterCode;

            @JsonProperty("totalGames")
            private Integer totalGames;

            @JsonProperty("wins")
            private Integer wins;

            @JsonProperty("top3")
            private Integer top3;

            @JsonProperty("averageRank")
            private Double averageRank;

            @JsonProperty("averageKills")
            private Double averageKills;

            @JsonProperty("averageAssistants")
            private Double averageAssistants;
        }
    }

    /**
     * 사용자 통계 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class UserStatsResponse {
        @JsonProperty("userNum")
        private Long userNum;

        @JsonProperty("seasonId")
        private Integer seasonId;

        @JsonProperty("matchingMode")
        private Integer matchingMode;

        @JsonProperty("matchingTeamMode")
        private Integer matchingTeamMode;

        @JsonProperty("mmr")
        private Integer mmr;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("rank")
        private Integer rank;

        @JsonProperty("rankSize")
        private Integer rankSize;

        @JsonProperty("totalGames")
        private Integer totalGames;

        @JsonProperty("totalWins")
        private Integer totalWins;

        @JsonProperty("averageRank")
        private Double averageRank;

        @JsonProperty("averageKills")
        private Double averageKills;

        @JsonProperty("averageAssistants")
        private Double averageAssistants;

        @JsonProperty("top1")
        private Integer top1;

        @JsonProperty("top2")
        private Integer top2;

        @JsonProperty("top3")
        private Integer top3;

        @JsonProperty("top5")
        private Integer top5;

        @JsonProperty("top7")
        private Integer top7;

        @JsonProperty("characterStats")
        private List<UserResponse.CharacterStats> characterStats;
    }

    /**
     * 게임 기록 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class GameResponse {
        @JsonProperty("userNum")
        private Long userNum;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("gameId")
        private Long gameId;

        @JsonProperty("seasonId")
        private Integer seasonId;

        @JsonProperty("matchingMode")
        private Integer matchingMode;

        @JsonProperty("matchingTeamMode")
        private Integer matchingTeamMode;

        @JsonProperty("characterNum")
        private Integer characterNum;

        @JsonProperty("characterLevel")
        private Integer characterLevel;

        @JsonProperty("gameRank")
        private Integer gameRank;

        @JsonProperty("playerKill")
        private Integer playerKill;

        @JsonProperty("playerAssistant")
        private Integer playerAssistant;

        @JsonProperty("monsterKill")
        private Integer monsterKill;

        @JsonProperty("bestWeapon")
        private Integer bestWeapon;

        @JsonProperty("bestWeaponLevel")
        private Integer bestWeaponLevel;

        @JsonProperty("masteryLevel")
        private List<MasteryLevel> masteryLevel;

        @JsonProperty("equipment")
        private List<Equipment> equipment;

        @JsonProperty("versionMajor")
        private Integer versionMajor;

        @JsonProperty("versionMinor")
        private Integer versionMinor;

        @JsonProperty("language")
        private String language;

        @JsonProperty("skillLevelInfo")
        private List<SkillLevel> skillLevelInfo;

        @JsonProperty("skillOrderInfo")
        private List<SkillOrder> skillOrderInfo;

        @JsonProperty("serverName")
        private String serverName;

        @JsonProperty("maxHp")
        private Integer maxHp;

        @JsonProperty("maxSp")
        private Integer maxSp;

        @JsonProperty("attackPower")
        private Integer attackPower;

        @JsonProperty("defense")
        private Integer defense;

        @JsonProperty("hpRegen")
        private Double hpRegen;

        @JsonProperty("spRegen")
        private Double spRegen;

        @JsonProperty("attackSpeed")
        private Double attackSpeed;

        @JsonProperty("moveSpeed")
        private Double moveSpeed;

        @JsonProperty("sightRange")
        private Double sightRange;

        @JsonProperty("gatheringSpeed")
        private Double gatheringSpeed;

        @JsonProperty("gainExp")
        private Integer gainExp;

        @JsonProperty("startDtm")
        private String startDtm;

        @JsonProperty("duration")
        private Integer duration;

        @JsonProperty("mmrGain")
        private Integer mmrGain;

        @JsonProperty("mmrAfter")
        private Integer mmrAfter;

        @Data
        @NoArgsConstructor
        public static class MasteryLevel {
            @JsonProperty("weaponType")
            private Integer weaponType;

            @JsonProperty("level")
            private Integer level;
        }

        @Data
        @NoArgsConstructor
        public static class Equipment {
            @JsonProperty("itemCode")
            private Integer itemCode;

            @JsonProperty("slotId")
            private Integer slotId;

            @JsonProperty("itemGrade")
            private Integer itemGrade;
        }

        @Data
        @NoArgsConstructor
        public static class SkillLevel {
            @JsonProperty("skillGroup")
            private Integer skillGroup;

            @JsonProperty("level")
            private Integer level;
        }

        @Data
        @NoArgsConstructor
        public static class SkillOrder {
            @JsonProperty("skillGroup")
            private Integer skillGroup;

            @JsonProperty("index")
            private Integer index;
        }

        // 유틸리티 메서드들
        public boolean isWin() {
            return gameRank != null && gameRank == 1;
        }

        public boolean isTop3() {
            return gameRank != null && gameRank <= 3;
        }

        public LocalDateTime getStartDateTime() {
            // startDtm을 LocalDateTime으로 변환하는 로직
            // 실제 구현에서는 적절한 파싱 로직 필요
            return LocalDateTime.now(); // placeholder
        }

        public String getFormattedDuration() {
            if (duration == null) return "00:00";
            int minutes = duration / 60;
            int seconds = duration % 60;
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    /**
     * 게임 목록 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class GameListResponse {
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("message")
        private String message;

        @JsonProperty("userGames")
        private List<GameResponse> userGames;

        @JsonProperty("next")
        private Integer next;
    }

    /**
     * 랭킹 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class RankingResponse {
        @JsonProperty("userNum")
        private Long userNum;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("rank")
        private Integer rank;

        @JsonProperty("mmr")
        private Integer mmr;

        @JsonProperty("beforeRank")
        private Integer beforeRank;

        @JsonProperty("beforeMmr")
        private Integer beforeMmr;

        @JsonProperty("totalGames")
        private Integer totalGames;

        @JsonProperty("totalWins")
        private Integer totalWins;

        @JsonProperty("averageRank")
        private Double averageRank;

        @JsonProperty("averageKills")
        private Double averageKills;

        @JsonProperty("averageAssistants")
        private Double averageAssistants;

        @JsonProperty("top1")
        private Integer top1;

        @JsonProperty("top2")
        private Integer top2;

        @JsonProperty("top3")
        private Integer top3;

        @JsonProperty("top5")
        private Integer top5;

        @JsonProperty("top7")
        private Integer top7;

        @JsonProperty("characterCode")
        private Integer characterCode;

        @JsonProperty("characterName")
        private String characterName;

        // 계산된 필드들
        public Double getWinRate() {
            if (totalGames == null || totalGames == 0) return 0.0;
            return (totalWins != null ? totalWins.doubleValue() : 0.0) / totalGames.doubleValue() * 100.0;
        }

        public Integer getMmrChange() {
            if (mmr == null || beforeMmr == null) return 0;
            return mmr - beforeMmr;
        }
    }

    /**
     * 랭킹 목록 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class RankingListResponse {
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("message")
        private String message;

        @JsonProperty("topRanks")
        private List<RankingResponse> topRanks;

        @JsonProperty("next")
        private Integer next;
    }

    /**
     * 캐릭터 정보 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class CharacterResponse {
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("name")
        private String name;

        @JsonProperty("engName")
        private String engName;

        @JsonProperty("resource")
        private String resource;

        @JsonProperty("faceResource")
        private String faceResource;

        @JsonProperty("backgroundResource")
        private String backgroundResource;

        @JsonProperty("rarity")
        private String rarity;

        @JsonProperty("releaseDate")
        private String releaseDate;

        @JsonProperty("skill")
        private List<Skill> skill;

        @Data
        @NoArgsConstructor
        public static class Skill {
            @JsonProperty("group")
            private Integer group;

            @JsonProperty("name")
            private String name;

            @JsonProperty("desc")
            private String desc;

            @JsonProperty("type")
            private String type;

            @JsonProperty("cooldown")
            private Double cooldown;
        }
    }

    /**
     * 캐릭터 목록 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class CharacterListResponse {
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("message")
        private String message;

        @JsonProperty("data")
        private List<CharacterResponse> data;
    }

    /**
     * 무기 정보 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class WeaponResponse {
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("type")
        private String type;

        @JsonProperty("name")
        private String name;

        @JsonProperty("engName")
        private String engName;

        @JsonProperty("resource")
        private String resource;

        @JsonProperty("attackType")
        private String attackType;

        @JsonProperty("attackRange")
        private String attackRange;

        @JsonProperty("attackSpeed")
        private String attackSpeed;

        @JsonProperty("damage")
        private String damage;

        @JsonProperty("critical")
        private String critical;

        @JsonProperty("attackPower")
        private Integer attackPower;

        @JsonProperty("defense")
        private Integer defense;

        @JsonProperty("maxHp")
        private Integer maxHp;

        @JsonProperty("maxSp")
        private Integer maxSp;

        @JsonProperty("hpRegen")
        private Double hpRegen;

        @JsonProperty("spRegen")
        private Double spRegen;

        @JsonProperty("attackSpeedRatio")
        private Double attackSpeedRatio;

        @JsonProperty("moveSpeed")
        private Double moveSpeed;

        @JsonProperty("sightRange")
        private Double sightRange;
    }

    /**
     * 무기 목록 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class WeaponListResponse {
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("message")
        private String message;

        @JsonProperty("data")
        private List<WeaponResponse> data;
    }

    /**
     * API 에러 응답 DTO
     */
    @Data
    @NoArgsConstructor
    public static class ErrorResponse {
        @JsonProperty("code")
        private Integer code;

        @JsonProperty("message")
        private String message;

        @JsonProperty("detail")
        private String detail;
    }

    /**
     * 시즌 정보 DTO
     */
    @Data
    @NoArgsConstructor
    public static class SeasonInfo {
        @JsonProperty("seasonId")
        private Integer seasonId;

        @JsonProperty("seasonName")
        private String seasonName;

        @JsonProperty("isCurrent")
        private Boolean isCurrent;

        @JsonProperty("startDate")
        private String startDate;

        @JsonProperty("endDate")
        private String endDate;
    }
}