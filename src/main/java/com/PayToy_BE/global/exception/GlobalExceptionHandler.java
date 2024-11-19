package com.PayToy_BE.global.exception;

import com.PayToy_BE.global.exception.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.PayToy_BE.global.exception.BaseErrorMessage.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 로그 형식
    private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";

    // 사용자 정의 예외 처리
    @ExceptionHandler(BaseErrorException.class)
    public ResponseEntity<ApiResponse<Void>> handle(BaseErrorException e) {

        logWarning(e, e.getErrorStatus());
        ApiResponse<Void> response = ApiResponse.fail(e.getErrorStatus() ,e.getErrorCode(), e.getMessage());

        return ResponseEntity
                .status(e.getErrorStatus())
                .body(response);
    }

    // @Valid 예외 처리 (@NotNull, @Size, etc...) or IllegalArgumentException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handle(MethodArgumentNotValidException e) {

        logWarning(e, ERROR_CODE.getStatus());
        ApiResponse<Void> response = ApiResponse.fail(ERROR_CODE.getStatus(),ERROR_CODE.getCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return ResponseEntity
                .status(ERROR_CODE.getStatus())
                .body(response);
    }

    // 서버 측 에러 (이외의 에러)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handle(Exception e) {

        logWarning(e, SERVER_ERROR_CODE.getStatus());
        ApiResponse<Void> response = ApiResponse.fail(SERVER_ERROR_CODE.getStatus(), SERVER_ERROR_CODE.getCode() , e.getMessage());

        return ResponseEntity
                .status(SERVER_ERROR_CODE.getStatus())
                .body(response);        }

    // log.warn이 중복되어 리팩토링
    private void logWarning(Exception e, int errorCode) {
        log.warn(e.getMessage(), e);  // 전체 로그 출력, 운영 단계에서는 삭제
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, e.getMessage());
    }
}