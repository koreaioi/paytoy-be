package com.PayToy_BE.domain.account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountErrorMessage {

    ACCOUNT_NOT_FOUND(400, "U-400-1", "계좌가 존재하지 않습니다."),
    ACCOUNT_BALANCE_LACK(400, "U-400-2", "계좌 잔액이 부족합니다.");

    final int status;
    final String code;
    final String message;

}
