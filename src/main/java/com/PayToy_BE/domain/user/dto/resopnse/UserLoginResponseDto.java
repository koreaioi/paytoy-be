package com.PayToy_BE.domain.user.dto.resopnse;

public record UserLoginResponseDto(
    Long userId,
    String sessionId
){
    public static UserLoginResponseDto from(Long userId, String session_id) {
        return new UserLoginResponseDto(userId, session_id);
    }
}
