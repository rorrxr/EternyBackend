package com.company.project_name.player.repository;

import com.company.project_name.player.entity.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {

    List<PlayerProfile> findByNicknameContainingIgnoreCase(String keyword);
}
