package com.PayToy_BE.domain.user.exception;

import com.PayToy_BE.global.exception.BaseErrorException;

import static com.PayToy_BE.domain.user.exception.UserErrorMessage.*;

public abstract class UserErrorException {

    public static class UserNotFoundException extends BaseErrorException {
        public UserNotFoundException() {
            super(USER_NOT_FOUND.getStatus(), USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage());
        }
    }

    public static class TelDuplicateException extends BaseErrorException {
        public TelDuplicateException() {
            super(TEL_DUPLICATED.getStatus(), TEL_DUPLICATED.getCode(), TEL_DUPLICATED.getMessage());
        }
    }

}
