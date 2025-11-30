package com.example.board.controller;

import com.example.board.common.ApiResponse;
import com.example.board.dto.request.UserCreateRequest;
import com.example.board.dto.request.UserLoginRequest;
import com.example.board.dto.response.LoginResponse;
import com.example.board.dto.response.UserResponse;
import com.example.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<UserResponse> signup(@Valid @RequestBody UserCreateRequest request) {
        return ApiResponse.ok(userService.signup(request));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        return ApiResponse.ok(userService.login(request));
    }
}