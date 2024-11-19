package com.PayToy_BE.domain.account.exception;

import com.PayToy_BE.global.exception.BaseErrorException;

import static com.PayToy_BE.domain.account.exception.AccountErrorMessage.ACCOUNT_NOT_FOUND;

public class CustomAccountNotFoundException extends BaseErrorException {
    public CustomAccountNotFoundException() {
        super(ACCOUNT_NOT_FOUND.getStatus(), ACCOUNT_NOT_FOUND.getCode(), ACCOUNT_NOT_FOUND.getMessage());
    }
}
