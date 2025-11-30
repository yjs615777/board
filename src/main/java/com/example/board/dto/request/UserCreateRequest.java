package com.example.board.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotBlank(message = "Username is required.")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 4, message = "Password must be at least 4 characters.")
    private String password;

    @Email(message = "Invalid email format.")
    private String email;
}
