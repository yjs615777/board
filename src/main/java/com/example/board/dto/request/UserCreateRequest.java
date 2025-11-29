package com.example.board.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotBlank(message = "아이디는 필수입니다")
    @Size(min = 2, max = 50, message = "아이디는 2~50자입니다")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Size(min = 4, message = "비밀번호는 4자 이상입니다")
    private String password;

    @Email(message = "이메일 형식이 아닙니다")
    private String email;
}
