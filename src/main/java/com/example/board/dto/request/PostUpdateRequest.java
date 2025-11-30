package com.example.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostUpdateRequest {

    @NotBlank(message = "Title is required.")
    @Size(max = 50, message = "Title must be 50 characters or fewer.")
    private String title;

    @NotBlank(message = "Content is required.")
    private String content;
}