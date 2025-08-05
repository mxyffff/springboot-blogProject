package me.leedakyeom.springbootblogproject.service;

import lombok.RequiredArgsConstructor;
import me.leedakyeom.springbootblogproject.domain.User;
import me.leedakyeom.springbootblogproject.repository.UserRepsitory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepsitory userRepsitory;

    // 사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String email) {
        return userRepsitory.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
