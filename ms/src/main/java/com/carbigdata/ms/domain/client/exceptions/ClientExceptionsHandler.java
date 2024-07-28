package com.carbigdata.ms.domain.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carbigdata.ms.domain.exceptions.ApiResponseError;

@ControllerAdvice

public class ClientExceptionsHandler {
    @ExceptionHandler(PasswordOrCpfNotMatch.class)
    public ResponseEntity<ApiResponseError> handlePasswordOrCpfNotMatch(PasswordOrCpfNotMatch exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleClientNotFoundException(ClientNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ApiResponseError> handleClientAlreadyExistsException(ClientAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.CONFLICT));
    }
}
