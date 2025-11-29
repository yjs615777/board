package com.example.board.repository;

import com.example.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // 게시글의 댓글 목록 (오래된순)
    List<Comment> findByPostIdOrderByCreatedAtAsc(Integer postId);
}