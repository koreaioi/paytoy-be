package com.PayToy_BE.domain.account.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AccountBalanceRequstDto(

        String accountNumber,
        @NotNull(message = "금액을 입력해주세요.")
        @PositiveOrZero(message = "금액은 양수입니다.")
        @Digits(integer = 10, fraction = 0, message = "금액은 숫자로 입력해야 하며 소수점을 포함할 수 없습니다")
        Long balance

) {
}
