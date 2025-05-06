package com.company.eterny.nickname.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nickname")
public class Nickname {
    @Id
    @GeneratedValue
    private Long id;      // DB PK
    @Column(nullable = false, unique = true)
    private Long userNum;                      // BSER API userNum
    @Column(nullable = false)
    private String nickname;
    // + createdAt, updatedAt 등 필요 필드
}