package com.bilgeadam.exception;

import com.bilgeadam.exception.custom.UserEmailExistsException;
import com.bilgeadam.exception.custom.UserNameNotFoundException;
import com.bilgeadam.exception.custom.UserWrongPasswordException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalException {

    private ErrorMessage createErrorMessage(EErrorType errorType, Exception exception){
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(UserExceptionHandler.class)
    public ResponseEntity<ErrorMessage> handleUserNameNotFoundException(UserExceptionHandler exception){
        EErrorType errorType = EErrorType.LOGIN_ERROR_USERNAME_DOES_NOT_EXIST;
        HttpStatus httpStatus = errorType.getHttpStatus();
        return new ResponseEntity<>(createErrorMessage(errorType,exception), httpStatus);
    }

    @ResponseBody
    @ExceptionHandler(UserEmailExistsException.class)
    public ResponseEntity<ErrorMessage> handleUserEmailExistsException(UserEmailExistsException exception){
        EErrorType errorType = EErrorType.REGISTER_ERROR_EMAIL_EXISTS;
        HttpStatus httpStatus = errorType.getHttpStatus();
        return new ResponseEntity<>(createErrorMessage(errorType, exception), httpStatus);
    }
    @ResponseBody
    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNameNotFoundException(UserNameNotFoundException exception){
        EErrorType errorType = EErrorType.LOGIN_USER_AND_PASSWORD_EXCEPTION;
        HttpStatus httpStatus = errorType.getHttpStatus();
        return new ResponseEntity<>(createErrorMessage(errorType, exception), httpStatus);
    }
    @ResponseBody
    @ExceptionHandler(UserWrongPasswordException.class)
    public ResponseEntity<ErrorMessage> handleUserWrongPasswordException(UserWrongPasswordException exception){
        EErrorType errorType = EErrorType.LOGIN_ERROR_WRONG_PASSWORD;
        HttpStatus httpStatus = errorType.getHttpStatus();
        return new ResponseEntity<>(createErrorMessage(errorType, exception), httpStatus);
    }


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentException(MethodArgumentNotValidException exception){
        EErrorType errorType = EErrorType.METHOD_NOT_VALID_ARGUMENT_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType, exception), errorType.getHttpStatus());
    }

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorMessage> handleInvalidFormatException(InvalidFormatException exception){
        EErrorType errorType = EErrorType.INVALID_PARAMETER;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleMethodMismatchException(MethodArgumentTypeMismatchException exception){
        EErrorType errorType = EErrorType.METHOD_MIS_MATCH_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        EErrorType errorType = EErrorType.METHOD_MIS_MATCH_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }
}
