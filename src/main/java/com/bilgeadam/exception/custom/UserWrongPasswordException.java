package com.bilgeadam.exception.custom;

import com.bilgeadam.exception.EErrorType;
import lombok.Getter;

@Getter
public class UserWrongPasswordException extends RuntimeException{

    final EErrorType errorType;

    public UserWrongPasswordException(EErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
