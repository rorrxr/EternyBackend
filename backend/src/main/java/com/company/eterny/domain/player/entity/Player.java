package com.company.eterny.domain.player.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "players")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    private Long userNum;  // BSER API의 userNum 그대로 사용

    @Column(nullable = false)
    private String nickname;

    private Integer mmr;
    private String tier;
    private Integer totalGames;
    private Integer wins;
    private Double winRate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // 간단한 업데이트 메서드
    public void updateFromBser(String nickname, Integer mmr, String tier) {
        this.nickname = nickname;
        this.mmr = mmr;
        this.tier = tier;
    }
}