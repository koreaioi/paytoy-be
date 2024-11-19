package com.PayToy_BE.domain.user.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorMessage {

    USER_NOT_FOUND(400, "U-400-1", "해당되는 유저가 없습니다."),
    TEL_DUPLICATED(400,"U-400-2", "전화번호가 중복됩니다.");

    final int status;
    final String code;
    final String message;
}
