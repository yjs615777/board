package com.example.board.service;

import com.example.board.domain.Comment;
import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.dto.request.CommentCreateRequest;
import com.example.board.dto.response.CommentResponse;
import com.example.board.exception.custom.UnauthorizedException;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    // 댓글 목록 조회
    public List<CommentResponse> getList(Integer postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    // 댓글 작성
    @Transactional
    public CommentResponse create(Integer postId, CommentCreateRequest request, Integer userId) {
        Post post = postService.findById(postId);
        User user = userService.findById(userId);

        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .build();

        Comment saved = commentRepository.save(comment);
        return CommentResponse.from(saved);
    }

    // 댓글 삭제
    @Transactional
    public void delete(Integer commentId, Integer userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("The comment does not exist."));

        if (!comment.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You do not have permission to delete this.");
        }

        commentRepository.delete(comment);
    }
}