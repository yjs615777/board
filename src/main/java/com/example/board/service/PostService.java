package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.dto.request.PostCreateRequest;
import com.example.board.dto.request.PostUpdateRequest;
import com.example.board.dto.response.PostDetailResponse;
import com.example.board.dto.response.PostResponse;
import com.example.board.exception.custom.PostNotFoundException;
import com.example.board.exception.custom.UnauthorizedException;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    // 게시글 목록 (페이징)
    public Page<PostResponse> getList(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedAtDesc(pageable)
                .map(PostResponse::from);
    }

    @Transactional  // 조회수 증가하니까 쓰기 작업
    public PostDetailResponse getDetail(Integer postId) {
        Post post = findById(postId);
        post.increaseViewCount();  // 조회수 +1
        return PostDetailResponse.from(post);
    }

    // 수정용 조회 (조회수 증가 X)
    public PostDetailResponse getForEdit(Integer postId) {
        Post post = findById(postId);
        return PostDetailResponse.from(post);  // 조회수 증가 없음
    }

    // 게시글 작성
    @Transactional
    public PostResponse create(PostCreateRequest request, Integer userId) {
        User user = userService.findById(userId);

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        Post saved = postRepository.save(post);
        return PostResponse.from(saved);
    }

    // 게시글 수정
    @Transactional
    public PostResponse update(Integer postId, PostUpdateRequest request, Integer userId) {
        Post post = findById(postId);

        // 작성자 확인
        if (!post.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("수정 권한이 없습니다");
        }

        post.update(request.getTitle(), request.getContent());
        return PostResponse.from(post);
    }

    // 게시글 삭제
    @Transactional
    public void delete(Integer postId, Integer userId) {
        Post post = findById(postId);

        // 작성자 확인
        if (!post.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("삭제 권한이 없습니다");
        }

        postRepository.delete(post);
    }

    // 내부용: ID로 게시글 찾기
    public Post findById(Integer postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("존재하지 않는 게시글입니다"));
    }
}