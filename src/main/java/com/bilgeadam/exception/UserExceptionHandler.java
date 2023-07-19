package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class UserExceptionHandler extends RuntimeException{
    final EErrorType errorType;

    public UserExceptionHandler(EErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
