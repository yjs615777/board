package com.example.board.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 필드 받는 생성자 자동 생성
public class ApiResponse<T> {

    private boolean success;  // 성공 여부
    private T data;           // 실제 데이터
    private String message;   // 에러 메시지

    // 성공 + 데이터
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

    // 성공만
    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(true, null, null);
    }

    // 실패 + 메시지
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, null, message);
    }

    // 실패만
    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<>(false, null, null);
    }
}