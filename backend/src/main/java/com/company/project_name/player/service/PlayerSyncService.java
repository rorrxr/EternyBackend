package com.company.project_name.player.service;

import com.company.project_name.player.entity.PlayerProfile;
import com.company.project_name.player.repository.PlayerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerSyncService {

    private final PlayerProfileRepository playerProfileRepository;

    public PlayerProfile upsert(long userNum, String nickname) {
        return playerProfileRepository.findById(userNum)
                .map(profile -> {
                    profile.updateNickname(nickname);
                    return playerProfileRepository.save(profile);
                })
                .orElseGet(() -> playerProfileRepository.save(
                        PlayerProfile.builder()
                                .userNum(userNum)
                                .nickname(nickname)
                                .build()
                ));
    }
}
