package com.PayToy_BE.global.exception;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseErrorMessage {

    ERROR_CODE(400, "E-400-1"),
    SERVER_ERROR_CODE(500, "E-500-2");

    private final int status;
    private final String code;
}
