package com.example.board.dto.response;

import com.example.board.domain.Post;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostDetailResponse {

    private Long id;
    private String title;
    private String content;
    private String authorName;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<CommentResponse> comments;

    public static PostDetailResponse from(Post post) {
        return PostDetailResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getUser().getUsername())
                .viewCount(post.getViewCount())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .comments(
                        post.getComments().stream()
                                .map(CommentResponse::from)
                                .toList()
                )
                .build();
    }
}