package com.example.board.controller;

import com.example.board.common.ApiResponse;
import com.example.board.dto.request.CommentCreateRequest;
import com.example.board.dto.response.CommentResponse;
import com.example.board.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 목록
    @GetMapping
    public ApiResponse<List<CommentResponse>> getList(@PathVariable Integer postId) {
        return ApiResponse.ok(commentService.getList(postId));
    }

    // 댓글 작성
    @PostMapping
    public ApiResponse<CommentResponse> create(
            @PathVariable Integer postId,
            @Valid @RequestBody CommentCreateRequest request,
            HttpServletRequest httpRequest) {
        Integer userId = (Integer) httpRequest.getAttribute("userId");
        return ApiResponse.ok(commentService.create(postId, request, userId));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ApiResponse<Void> delete(
            @PathVariable Integer postId,
            @PathVariable Integer commentId,
            HttpServletRequest httpRequest) {
        Integer userId = (Integer) httpRequest.getAttribute("userId");
        commentService.delete(commentId, userId);
        return ApiResponse.ok();
    }
}