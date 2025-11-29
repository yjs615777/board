package com.example.board.repository;

import com.example.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends JpaRepository<Post, Integer> {
    // 전체 목록 (최신순)
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}