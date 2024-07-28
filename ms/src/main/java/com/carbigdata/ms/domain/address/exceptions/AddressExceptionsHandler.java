package com.carbigdata.ms.domain.address.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carbigdata.ms.domain.exceptions.ApiResponseError;

@ControllerAdvice
public class AddressExceptionsHandler {
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleClientNotFoundException(AddressNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.NOT_FOUND));
    }
}
