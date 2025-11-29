package com.example.board.dto.response;

import com.example.board.domain.Post;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {

    private Integer id;
    private String title;
    private String authorName;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .authorName(post.getUser().getUsername())   // User 엔티티 필드명에 맞게 수정
                .viewCount(post.getViewCount())
                .build();
    }
}