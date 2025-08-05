package me.leedakyeom.springbootblogproject.repository;

import me.leedakyeom.springbootblogproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepsitory extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 이메일로 사용자 정보를 가져옴
}
