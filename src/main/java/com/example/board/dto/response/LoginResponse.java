package com.example.board.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private Integer id;
    private String username;
    private String token;  // JWT 토큰
}