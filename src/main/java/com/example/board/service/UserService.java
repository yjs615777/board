package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.dto.request.UserCreateRequest;
import com.example.board.dto.request.UserLoginRequest;
import com.example.board.dto.response.UserResponse;
import com.example.board.exception.custom.UnauthorizedException;
import com.example.board.exception.custom.UserNotFoundException;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor  // final 필드 생성자 자동 생성
@Transactional(readOnly = true)  // 기본은 읽기 전용
public class UserService {

    private final UserRepository userRepository;

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
                .passwordHash(request.getPassword())  // TODO: 암호화
                .email(request.getEmail())
                .build();

        User saved = userRepository.save(user);
        return UserResponse.from(saved);
    }

    // 로그인 (간단 버전 - 실제로는 JWT 등 사용)
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 이메일입니다"));

        // 비밀번호 확인 (실제로는 암호화된 비밀번호 비교)
        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다");
        }

        return UserResponse.from(user);
    }

    // ID로 유저 조회 (내부용)
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다"));
    }
}