package com.PayToy_BE.domain.user.controller;

import com.PayToy_BE.domain.user.dto.request.UserRegisterRequestDto;
import com.PayToy_BE.domain.user.dto.resopnse.UserResponseDto;
import com.PayToy_BE.domain.user.service.UserService;
import com.PayToy_BE.global.exception.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.PayToy_BE.domain.user.controller.Success.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 회원가입
    @PostMapping
    public ApiResponse<Void> registerUser(@RequestBody UserRegisterRequestDto requestDto) {

        userService.saveUser(requestDto);
        return ApiResponse.success(
                USER_SUCCESS_REGISTER.getStatus(),
                USER_SUCCESS_REGISTER.getCode(),
                USER_SUCCESS_REGISTER.getMessage());
    }

    // 유저 ID로 조회
    @GetMapping("/{userId}")
    public ApiResponse<UserResponseDto> getUser(@PathVariable Long userId) {
        UserResponseDto response = UserResponseDto.toDto(userService.findUserById(userId));
        return ApiResponse.success(
                USER_SUCCESS_RESPONSE.getStatus(),
                USER_SUCCESS_RESPONSE.getCode(),
                USER_SUCCESS_RESPONSE.getMessage(),
                response);
    }


    @GetMapping("/tel/{tel}")
    public ApiResponse<UserResponseDto> checkTel(@PathVariable("tel") String tel) {
        userService.validateTel(tel);
        return ApiResponse.success(
                USER_VALIDATE_TEL.getStatus(),
                USER_VALIDATE_TEL.getCode(),
                USER_VALIDATE_TEL.getMessage());
    }
}
