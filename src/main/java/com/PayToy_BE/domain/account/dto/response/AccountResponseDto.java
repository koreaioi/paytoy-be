package com.PayToy_BE.domain.account.dto.response;

import com.PayToy_BE.domain.account.entity.Account;

public record AccountResponseDto (

        Long account_id,      // 계좌 PK
        String accountNumber, // 계좌 번호
        Long Balance          // 잔액

){
    public static AccountResponseDto from(Account account) {
        return new AccountResponseDto(account.getId(), account.getAccountNumber(), account.getBalance());
    }
}
