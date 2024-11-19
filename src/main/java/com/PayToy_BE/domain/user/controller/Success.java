package com.PayToy_BE.domain.user.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Success {

    USER_SUCCESS_REGISTER(200, "U-200-1", "유저 회원가입 성공"),
    USER_SUCCESS_RESPONSE(200, "U-200-2", "유저 조회 성공"),
    USER_VALIDATE_TEL(200,"U-200-3","사용 가능한 전화번호 입니다");

    private final int status;
    private final String code;
    private final String message;

}
