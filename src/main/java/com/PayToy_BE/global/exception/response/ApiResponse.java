package com.PayToy_BE.global.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;
    private String code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> fail(int status, String code, String message) {
        return new ApiResponse<>(status, code, message,null);
    }

    // 자바 기본 에러 처리 형식
    public static <T> ApiResponse<T> fail(int status, String message) {
        return new ApiResponse<>(status, null, message,null);
    }

    public static <T> ApiResponse<T> success(int status,String code, String message) {
        return new ApiResponse<>(status, code, message,null);
    }

    public static <T> ApiResponse<T> success(int status,String code, String message, T data) {
        return new ApiResponse<>(status, code, message,data);
    }

}
