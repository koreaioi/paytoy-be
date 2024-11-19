package com.PayToy_BE.domain.account.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Success {

    ACCOUNT_REGISTER_SUCCESS(200, "A-200-1", "계좌 생성 완료"),
    ACCOUNT_DEPOSIT_SUCCESS(200, "A-200-2", "입금 완료"),
    ACCOUNT_WITHDRAW_SUCCESS(200, "A-200-3", "출금 완료"),
    ACCOUNT_TRANSFER_SUCCESS(200, "A-200-3", "송금(이체) 완료"),
    ACCOUNT_GET_SUCCESS(200, "A-200-4", "계좌 조회 성공");

    private final int status;
    private final String code;
    private final String message;


}
