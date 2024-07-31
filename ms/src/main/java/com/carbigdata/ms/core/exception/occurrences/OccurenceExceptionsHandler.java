package com.carbigdata.ms.core.exception.occurrences;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carbigdata.ms.presentation.exceptions.ApiResponseError;

@ControllerAdvice
public class OccurenceExceptionsHandler {
    @ExceptionHandler(OccurrenceNotBeModifiedException.class)
    public ResponseEntity<ApiResponseError> handleOccurrenceNotBeModifiedException(OccurrenceNotBeModifiedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }


    @ExceptionHandler(OccurrenceNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleOccurrenceNotFoundException(OccurrenceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.NOT_FOUND));
    }

}
