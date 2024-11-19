package com.PayToy_BE.domain.account.entity;


import com.PayToy_BE.domain.account.exception.BalanceLackException;
import com.PayToy_BE.domain.user.entity.User;
import com.PayToy_BE.global.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;


@Entity
@Table(name="accounts") // account는 MySQL 예약어 -> 테이블 명을 accounts로 명시
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity {

    @Id @Column(name="account_id")
    @GeneratedValue
    private Long id;

    private String accountNumber; // 계좌 번호
    private Long balance;         // 잔액

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;            // 계좌 소유주

    // 빌더 패턴 사용 - 코드 가독성 향상
    @Builder
    private Account(User user) {
        this.accountNumber = generateAccountNumber();
        balance = 1000L; // 계좌 개설 시 1000원
        this.user = user;
    }

    // 실제 사용하는 DTO -> User 변환 메서드
    public static Account from(User user) {
        return Account.builder().user(user).build();
    }

    // 계좌 번호 생성 도메인 로직
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder("1002");

        // 8자리 무작위 숫자 생성
        for (int i = 0; i < 8; i++) {
            accountNumberBuilder.append(random.nextInt(10)); // 0~9 사이 숫자 추가
        }

        return accountNumberBuilder.toString();
    }

    public void deposit(Long money){
        this.balance += money;
    }

    public void withdraw(Long money){
        if(balance - money < 0){
            throw new BalanceLackException();
        }
        this.balance -= money;
    }
}
