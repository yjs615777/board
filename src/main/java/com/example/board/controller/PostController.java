package com.example.board.controller;

import com.example.board.common.ApiResponse;
import com.example.board.dto.request.PostCreateRequest;
import com.example.board.dto.request.PostUpdateRequest;
import com.example.board.dto.response.PostDetailResponse;
import com.example.board.dto.response.PostResponse;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 목록
    @GetMapping
    public ApiResponse<Page<PostResponse>> getList(Pageable pageable) {
        return ApiResponse.ok(postService.getList(pageable));
    }

    // 게시글 상세
    @GetMapping("/{postId}")
    public ApiResponse<PostDetailResponse> getDetail(@PathVariable Integer postId) {
        return ApiResponse.ok(postService.getDetail(postId));
    }

    // 게시글 작성
    @PostMapping
    public ApiResponse<PostResponse> create(
            @Valid @RequestBody PostCreateRequest request,
            @RequestParam Integer userId) {  // TODO: 나중에 로그인 연동
        return ApiResponse.ok(postService.create(request, userId));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ApiResponse<PostResponse> update(
            @PathVariable Integer postId,
            @Valid @RequestBody PostUpdateRequest request,
            @RequestParam Integer userId) {
        return ApiResponse.ok(postService.update(postId, request, userId));
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ApiResponse<Void> delete(
            @PathVariable Integer postId,
            @RequestParam Integer userId) {
        postService.delete(postId, userId);
        return ApiResponse.ok();
    }
    // 게시글 상세 (조회수 증가 X) - 수정 페이지용
    @GetMapping("/{postId}/edit")
    public ApiResponse<PostDetailResponse> getForEdit(@PathVariable Integer postId) {
        return ApiResponse.ok(postService.getForEdit(postId));
    }
}