package com.PayToy_BE.domain.user.dto.resopnse;

import com.PayToy_BE.domain.account.entity.Account;
import com.PayToy_BE.domain.user.entity.User;
import lombok.AllArgsConstructor;

import java.util.List;

public record UserResponseDto(
        Long userId,
        String tel,
        String username,
        List<Account> accounts
) {

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getTel(), user.getUsername(), user.getAccounts());
    }
}
