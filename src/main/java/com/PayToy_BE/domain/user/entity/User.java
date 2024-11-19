package com.PayToy_BE.domain.user.entity;


import com.PayToy_BE.domain.account.entity.Account;
import com.PayToy_BE.domain.user.dto.request.UserRegisterRequestDto;
import com.PayToy_BE.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id @Column(name="user_id")
    @GeneratedValue
    private Long id;

    private String username;
    private String tel;
    private String password;

    @OneToMany(mappedBy = "user")
    List<Account> accounts = new ArrayList<>();

    @Builder
    public User(String username, String tel, String password) {
        this.username = username;
        this.tel = tel;
        this.password = password;
    }

    public static User of(UserRegisterRequestDto requestDto, String password) {
        return User.builder()
                .username(requestDto.username())
                .tel(requestDto.tel())
                .password(password)
                .build();
    }
}
