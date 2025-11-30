package com.example.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import jakarta.validation.constraints.Email;

@Getter
public class UserLoginRequest {

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;
}