package com.PayToy_BE.global.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    SESSION_ID_ISNULL(401,"S-401-1", "SessionId가 없습니다."),
    SESSION_ID_INVALID(401,"S-401-2","SessionId가 유효하지 않습니다."),

    LOGIN_FAIL(401, "L-401-1", "로그인 실패");

    final int status;
    final String code;
    final String message;
}
