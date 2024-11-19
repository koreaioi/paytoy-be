package com.PayToy_BE.domain.account.controller;

import com.PayToy_BE.domain.account.dto.request.AccountBalanceRequstDto;
import com.PayToy_BE.domain.account.dto.request.AccountRegisterRequestDto;
import com.PayToy_BE.domain.account.dto.request.AccountTranferRequestDto;
import com.PayToy_BE.domain.account.dto.response.AccountResponseDto;
import com.PayToy_BE.domain.account.service.AccountService;
import com.PayToy_BE.global.exception.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.PayToy_BE.domain.account.controller.Success.*;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ApiResponse<AccountResponseDto> registerAccount(@RequestBody AccountRegisterRequestDto requestDto) {
        AccountResponseDto response = AccountResponseDto.from(accountService.saveAccount(requestDto));
        return ApiResponse.success(
                ACCOUNT_REGISTER_SUCCESS.getStatus(),
                ACCOUNT_REGISTER_SUCCESS.getCode(),
                ACCOUNT_REGISTER_SUCCESS.getMessage(),
                response
        );
    }

    @PatchMapping("/deposit")
    public ApiResponse<AccountResponseDto> depositAccount(@RequestBody AccountBalanceRequstDto requstDto) {
        AccountResponseDto response
                = AccountResponseDto.from(accountService.depositBalance(requstDto));

        return ApiResponse.success(
                ACCOUNT_DEPOSIT_SUCCESS.getStatus(),
                ACCOUNT_DEPOSIT_SUCCESS.getCode(),
                ACCOUNT_DEPOSIT_SUCCESS.getMessage(),
                response
        );
    }

    @PatchMapping("/withdraw")
    public ApiResponse<AccountResponseDto> withdrawAccount(@RequestBody AccountBalanceRequstDto requstDto) {
        AccountResponseDto response
                = AccountResponseDto.from(accountService.withdrawBalance(requstDto));

        return ApiResponse.success(
                ACCOUNT_WITHDRAW_SUCCESS.getStatus(),
                ACCOUNT_WITHDRAW_SUCCESS.getCode(),
                ACCOUNT_WITHDRAW_SUCCESS.getMessage(),
                response
        );
    }

    @PatchMapping("/transfer")
    public ApiResponse<AccountResponseDto> transferAccount(@RequestBody @Valid AccountTranferRequestDto requstDto) {
        accountService.transferBalance(requstDto);

        return ApiResponse.success(
                ACCOUNT_TRANSFER_SUCCESS.getStatus(),
                ACCOUNT_TRANSFER_SUCCESS.getCode(),
                ACCOUNT_TRANSFER_SUCCESS.getMessage()
        );
    }

    @GetMapping("/{accountNumber}")
    public ApiResponse<AccountResponseDto> getAccount(@PathVariable String accountNumber) {
        AccountResponseDto response
                = AccountResponseDto.from(accountService.findByAccountNumber(accountNumber));

        return ApiResponse.success(
                ACCOUNT_GET_SUCCESS.getStatus(),
                ACCOUNT_GET_SUCCESS.getCode(),
                ACCOUNT_GET_SUCCESS.getMessage(),
                response
        );
    }
}
