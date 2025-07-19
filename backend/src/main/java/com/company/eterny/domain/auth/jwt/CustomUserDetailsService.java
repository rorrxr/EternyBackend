package com.company.eterny.domain.auth.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

@Slf4j
// @Service
@RequiredArgsConstructor
public class CustomUserDetailsService { // implements UserDetailsService {
    // private final UserRepository userRepository;

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername called with username: {}", username);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("유저명을 통해 사용자를 찾을 수 없습니다: " + username));
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        log.debug("loadUserByEmail called with email: {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("이메일을 통해 사용자를 찾을 수 없습니다:" + email));
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        log.debug("loadUserById called with id: {}", id);
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("유저 ID를 통해 사용자를 찾을 수 없습니다: " + id));
        return new CustomUserDetails(user);
    }
    */
}
