package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.dto.request.UserCreateRequest;
import com.example.board.dto.request.UserLoginRequest;
import com.example.board.dto.response.LoginResponse;
import com.example.board.dto.response.UserResponse;
import com.example.board.exception.custom.UnauthorizedException;
import com.example.board.exception.custom.UserNotFoundException;
import com.example.board.repository.UserRepository;
import com.example.board.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor  // final 필드 생성자 자동 생성
@Transactional(readOnly = true)  // 기본은 읽기 전용
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    @Transactional  // DB에 저장하는 쓰기 작업이라 readOnly 해제
    public UserResponse signup(UserCreateRequest request) {
        // 중복 체크
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }

        // 유저 생성 (실제로는 비밀번호 암호화 필요)
        User user = User.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        User saved = userRepository.save(user);
        return UserResponse.from(saved);
    }

    // 로그인 (간단 버전 - 실제로는 JWT 등 사용)
    // 로그인 - LoginResponse로 변경
    public LoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email not found."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Incorrect password.");
        }

        // JWT 토큰 생성
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();
    }
    // ID로 유저 조회 (내부용)
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}