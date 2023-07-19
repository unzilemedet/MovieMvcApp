package com.bilgeadam.exception.custom;

import com.bilgeadam.exception.EErrorType;
import lombok.Getter;

@Getter
public class UserNameNotFoundException extends RuntimeException {
    final EErrorType errorType;

    public UserNameNotFoundException(EErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
