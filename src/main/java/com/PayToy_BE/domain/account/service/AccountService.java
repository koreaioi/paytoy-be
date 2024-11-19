package com.PayToy_BE.domain.account.service;

import com.PayToy_BE.domain.account.dto.request.AccountBalanceRequstDto;
import com.PayToy_BE.domain.account.dto.request.AccountRegisterRequestDto;
import com.PayToy_BE.domain.account.dto.request.AccountTranferRequestDto;
import com.PayToy_BE.domain.account.entity.Account;
import com.PayToy_BE.domain.account.exception.CustomAccountNotFoundException;
import com.PayToy_BE.domain.account.repository.AccountRepository;
import com.PayToy_BE.domain.user.entity.User;
import com.PayToy_BE.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    public Account saveAccount(AccountRegisterRequestDto requestDto) {
        User findUser = userService.findUserById(requestDto.userId());
        Account saveAccount = accountRepository.save(Account.from(findUser));
        findUser.getAccounts().add(saveAccount);

        return saveAccount;
    }

    @Transactional // 영속성 컨텍스트 조회 객체 더티 체킹과 무결성 보장
    public Account depositBalance(AccountBalanceRequstDto requestDto) {
        Account findAccount = checkAccountNumber(requestDto.accountNumber());
        findAccount.deposit(requestDto.balance());
        return findAccount;
    }

    @Transactional // 영속성 컨텍스트 조회 객체 더티 체킹과 무결성 보장
    public Account withdrawBalance(AccountBalanceRequstDto requestDto) {
        Account findAccount = checkAccountNumber(requestDto.accountNumber());
        findAccount.withdraw(requestDto.balance());
        return findAccount;
    }

    @Transactional // 영속성 컨텍스트 조회 객체 더티 체킹과 무결성 보장
    public void transferBalance(AccountTranferRequestDto requestDto) {
        String senderAccNum = requestDto.sendAccountNumber();
        String receiveAccNum = requestDto.receiveAccountNumber();
        Long balance = requestDto.balance();

        Account sendAccount = findByAccountNumber(senderAccNum);
        Account receiveAccount = findByAccountNumber(receiveAccNum);

        sendAccount.withdraw(balance);
        receiveAccount.deposit(balance);
    }


    public Account findByAccountNumber(String accountNumber) {
        return checkAccountNumber(accountNumber);
    }

    /*
    * 리팩토링
    * */

    private Account checkAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(CustomAccountNotFoundException::new);
    }

}
