package me.leedakyeom.springbootblogproject.service;


import lombok.RequiredArgsConstructor;
import me.leedakyeom.springbootblogproject.domain.User;
import me.leedakyeom.springbootblogproject.dto.AddUserRequest;
import me.leedakyeom.springbootblogproject.repository.UserRepsitory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepsitory userRepsitory;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepsitory.save(User.builder()
                .email(dto.getEmail())
                // 1. 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
