package com.company.eterny.player.repository;

import com.company.eterny.player.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // 닉네임으로 검색
    Optional<Player> findByNicknameIgnoreCase(String nickname);

    // MMR 순 랭킹 (간단하게)
    Page<Player> findAllByOrderByMmrDesc(Pageable pageable);

    // 특정 티어 랭킹
    Page<Player> findByTierOrderByMmrDesc(String tier, Pageable pageable);
}