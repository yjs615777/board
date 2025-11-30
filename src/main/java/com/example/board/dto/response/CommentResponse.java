package com.example.board.dto.response;

import com.example.board.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {

    private Integer id;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .authorName(comment.getUser().getUsername()) // 작성자 이름
                .createdAt(comment.getCreatedAt())           // 댓글 생성 시간
                .build();
    }
}