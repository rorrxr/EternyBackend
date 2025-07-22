package com.company.eterny.domain.game.repository;

import com.company.eterny.domain.game.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    // 기본 검색 메서드
    Optional<Game> findByGameSeq(Long gameSeq);

    boolean existsByGameSeq(Long gameSeq);

    // 특정 플레이어의 게임 기록 조회
    Page<Game> findByUserNumOrderByGameStartedAtDesc(Long userNum, Pageable pageable);

    List<Game> findByUserNumOrderByGameStartedAtDesc(Long userNum);

    // 최근 N개 게임 조회
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum ORDER BY g.gameStartedAt DESC")
    List<Game> findRecentGamesByUserNum(@Param("userNum") Long userNum, Pageable pageable);

    // 특정 기간 내 게임 기록 조회
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum AND g.gameStartedAt BETWEEN :startDate AND :endDate ORDER BY g.gameStartedAt DESC")
    Page<Game> findByUserNumAndGameStartedAtBetween(@Param("userNum") Long userNum,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate,
                                                    Pageable pageable);

    // 캐릭터별 게임 기록 조회
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum AND g.characterCode = :characterCode ORDER BY g.gameStartedAt DESC")
    Page<Game> findByUserNumAndCharacterCodeOrderByGameStartedAtDesc(@Param("userNum") Long userNum,
                                                                     @Param("characterCode") Integer characterCode,
                                                                     Pageable pageable);

    // 팀 모드별 게임 기록 조회
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum AND g.teamMode = :teamMode ORDER BY g.gameStartedAt DESC")
    Page<Game> findByUserNumAndTeamModeOrderByGameStartedAtDesc(@Param("userNum") Long userNum,
                                                                @Param("teamMode") Integer teamMode,
                                                                Pageable pageable);

    // 승리 게임만 조회
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum AND g.gameRank = 1 ORDER BY g.gameStartedAtDesc")
    Page<Game> findWinGamesByUserNum(@Param("userNum") Long userNum, Pageable pageable);

    // Top3 게임 조회
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum AND g.gameRank <= 3 ORDER BY g.gameStartedAt DESC")
    Page<Game> findTop3GamesByUserNum(@Param("userNum") Long userNum, Pageable pageable);

    // 통계 조회용 쿼리들
    @Query("SELECT COUNT(g) FROM Game g WHERE g.userNum = :userNum")
    Long countByUserNum(@Param("userNum") Long userNum);

    @Query("SELECT COUNT(g) FROM Game g WHERE g.userNum = :userNum AND g.gameRank = 1")
    Long countWinsByUserNum(@Param("userNum") Long userNum);

    @Query("SELECT COUNT(g) FROM Game g WHERE g.userNum = :userNum AND g.gameRank <= 3")
    Long countTop3ByUserNum(@Param("userNum") Long userNum);

    @Query("SELECT AVG(g.gameRank) FROM Game g WHERE g.userNum = :userNum")
    Double getAverageRankByUserNum(@Param("userNum") Long userNum);

    @Query("SELECT AVG(g.kills) FROM Game g WHERE g.userNum = :userNum")
    Double getAverageKillsByUserNum(@Param("userNum") Long userNum);

    @Query("SELECT AVG(g.assists) FROM Game g WHERE g.userNum = :userNum")
    Double getAverageAssistsByUserNum(@Param("userNum") Long userNum);

    @Query("SELECT SUM(g.mmrGain) FROM Game g WHERE g.userNum = :userNum")
    Long getTotalMmrGainByUserNum(@Param("userNum") Long userNum);

    // 캐릭터 사용 통계
    @Query("SELECT g.characterCode, g.characterName, COUNT(g) as gameCount FROM Game g WHERE g.userNum = :userNum GROUP BY g.characterCode, g.characterName ORDER BY gameCount DESC")
    List<Object[]> getCharacterUsageStatsByUserNum(@Param("userNum") Long userNum);

    @Query("SELECT g.characterCode, g.characterName, COUNT(g) as gameCount FROM Game g WHERE g.userNum = :userNum GROUP BY g.characterCode, g.characterName ORDER BY gameCount DESC")
    List<Object[]> getMostUsedCharacterByUserNum(@Param("userNum") Long userNum, Pageable pageable);

    // 무기 사용 통계
    @Query("SELECT g.weaponCode, g.weaponName, COUNT(g) as gameCount FROM Game g WHERE g.userNum = :userNum AND g.weaponCode IS NOT NULL GROUP BY g.weaponCode, g.weaponName ORDER BY gameCount DESC")
    List<Object[]> getWeaponUsageStatsByUserNum(@Param("userNum") Long userNum);

    // 최근 활동 확인
    @Query("SELECT MAX(g.gameStartedAt) FROM Game g WHERE g.userNum = :userNum")
    Optional<LocalDateTime> getLastGameDateByUserNum(@Param("userNum") Long userNum);

    // 시즌별 게임 기록
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum AND g.seasonId = :seasonId ORDER BY g.gameStartedAt DESC")
    Page<Game> findByUserNumAndSeasonIdOrderByGameStartedAtDesc(@Param("userNum") Long userNum,
                                                                @Param("seasonId") Integer seasonId,
                                                                Pageable pageable);

    // 특정 날짜의 게임 기록
    @Query("SELECT g FROM Game g WHERE g.userNum = :userNum AND DATE(g.gameStartedAt) = DATE(:targetDate) ORDER BY g.gameStartedAt DESC")
    List<Game> findByUserNumAndGameDate(@Param("userNum") Long userNum, @Param("targetDate") LocalDateTime targetDate);
}