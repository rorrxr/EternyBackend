package com.company.eterny.domain.player.repository;

import com.company.eterny.domain.player.entity.Player;
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
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // 기본 검색 메서드
    Optional<Player> findByUserNum(Long userNum);

    Optional<Player> findByNickname(String nickname);

    boolean existsByUserNum(Long userNum);

    boolean existsByNickname(String nickname);

    // 닉네임 검색 (대소문자 무시, 부분 일치)
    @Query("SELECT p FROM Player p WHERE LOWER(p.nickname) LIKE LOWER(CONCAT('%', :nickname, '%'))")
    List<Player> findByNicknameContainingIgnoreCase(@Param("nickname") String nickname);

    // 페이징된 닉네임 검색
    @Query("SELECT p FROM Player p WHERE LOWER(p.nickname) LIKE LOWER(CONCAT('%', :nickname, '%')) ORDER BY p.mmr DESC")
    Page<Player> findByNicknameContainingIgnoreCaseOrderByMmrDesc(@Param("nickname") String nickname, Pageable pageable);

    // MMR 기준 순위 조회
    @Query("SELECT p FROM Player p WHERE p.mmr IS NOT NULL ORDER BY p.mmr DESC")
    Page<Player> findAllOrderByMmrDesc(Pageable pageable);

    // 티어별 플레이어 조회
    @Query("SELECT p FROM Player p WHERE p.rank BETWEEN :minRank AND :maxRank ORDER BY p.mmr DESC")
    Page<Player> findByRankBetweenOrderByMmrDesc(@Param("minRank") Integer minRank,
                                                 @Param("maxRank") Integer maxRank,
                                                 Pageable pageable);

    // 승률 기준 조회
    @Query("SELECT p FROM Player p WHERE p.winRate >= :minWinRate AND p.totalGames >= :minGames ORDER BY p.winRate DESC")
    Page<Player> findByWinRateGreaterThanEqualAndTotalGamesGreaterThanEqualOrderByWinRateDesc(
            @Param("minWinRate") Double minWinRate,
            @Param("minGames") Integer minGames,
            Pageable pageable);

    // 활성 플레이어 조회 (최근 30일 내 게임 기록이 있는 플레이어)
    @Query("SELECT p FROM Player p WHERE p.lastGameDate >= :since ORDER BY p.lastGameDate DESC")
    Page<Player> findActivePlayersSince(@Param("since") LocalDateTime since, Pageable pageable);

    // 특정 캐릭터를 주로 사용하는 플레이어 조회
    @Query("SELECT p FROM Player p WHERE p.mostCharacterCode = :characterCode ORDER BY p.mmr DESC")
    Page<Player> findByMostCharacterCodeOrderByMmrDesc(@Param("characterCode") Integer characterCode, Pageable pageable);

    // 통계 조회용 쿼리
    @Query("SELECT COUNT(p) FROM Player p WHERE p.rank BETWEEN :minRank AND :maxRank")
    Long countByRankBetween(@Param("minRank") Integer minRank, @Param("maxRank") Integer maxRank);

    @Query("SELECT AVG(p.mmr) FROM Player p WHERE p.mmr IS NOT NULL")
    Double getAverageMmr();

    @Query("SELECT AVG(p.winRate) FROM Player p WHERE p.winRate IS NOT NULL AND p.totalGames >= :minGames")
    Double getAverageWinRate(@Param("minGames") Integer minGames);

    // 상위 플레이어 조회
    @Query("SELECT p FROM Player p WHERE p.mmr IS NOT NULL ORDER BY p.mmr DESC")
    List<Player> findTop10ByOrderByMmrDesc(Pageable pageable);

    // 최근 업데이트된 플레이어 조회
    @Query("SELECT p FROM Player p ORDER BY p.updatedAt DESC")
    Page<Player> findRecentlyUpdatedPlayers(Pageable pageable);

    // 게임 수 기준 조회
    @Query("SELECT p FROM Player p WHERE p.totalGames >= :minGames ORDER BY p.totalGames DESC")
    Page<Player> findByTotalGamesGreaterThanEqualOrderByTotalGamesDesc(@Param("minGames") Integer minGames, Pageable pageable);
}