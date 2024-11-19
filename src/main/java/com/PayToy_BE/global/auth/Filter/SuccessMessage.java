package com.PayToy_BE.global.auth.Filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    LOGIN_SUCCESS(201,"L-201-1","로그인에 성공했습니다.");

    final int status;
    final String code;
    final String message;
}
