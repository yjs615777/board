package com.example.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostCreateRequest {

    @NotBlank(message = "제목은 필수입니다")
    @Size(max = 50, message = "제목은 50자 이하입니다")
    private String title;

    @NotBlank(message = "내용은 필수입니다")
    private String content;
}