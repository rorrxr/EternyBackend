package com.company.project_name.auth.jwt.repository;

import com.company.project_name.auth.jwt.entity.JwtToken;
import com.company.project_name.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByUserAndDeviceInfo_DeviceId(User user, String deviceId);

    boolean existsByUserId(Long userId);
    boolean existsByUserIdAndDeviceInfo_DeviceId(Long userId, String deviceId);
}
