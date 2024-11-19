package com.PayToy_BE.domain.account.exception;

import com.PayToy_BE.global.exception.BaseErrorException;

import static com.PayToy_BE.domain.account.exception.AccountErrorMessage.ACCOUNT_BALANCE_LACK;

public class BalanceLackException extends BaseErrorException {
    public BalanceLackException() {
        super(ACCOUNT_BALANCE_LACK.getStatus(), ACCOUNT_BALANCE_LACK.getCode(), ACCOUNT_BALANCE_LACK.getMessage());
    }
}
