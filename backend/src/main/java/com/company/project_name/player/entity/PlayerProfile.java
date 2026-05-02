package com.company.project_name.player.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "player_profiles")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerProfile {

    @Id
    @Column(name = "user_num")
    private Long userNum;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "last_seen_season")
    private Integer lastSeenSeason;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateLastSeenSeason(int seasonId) {
        this.lastSeenSeason = seasonId;
    }
}
