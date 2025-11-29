package com.example.board.repository;

import com.example.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 로그인용: email으로 유저 찾기
    Optional<User> findByEmail(String email);
    // username 중복 체크
    boolean existsByUsername(String username);
    // email 중복 체크
    boolean existsByEmail(String email);
}