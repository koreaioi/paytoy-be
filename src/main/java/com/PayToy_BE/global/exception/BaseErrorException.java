package com.PayToy_BE.global.exception;

import lombok.Getter;

@Getter
public class BaseErrorException extends RuntimeException {

    private final int ErrorStatus;
    private final String ErrorCode;
    
    public BaseErrorException(int errorStatus, String errorCode, String errorMessage) {
        super(errorMessage);
        ErrorStatus = errorStatus;
        ErrorCode = errorCode;
    }
}