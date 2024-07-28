package com.carbigdata.ms.domain.occurrences_image.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carbigdata.ms.domain.exceptions.ApiResponseError;

@ControllerAdvice
public class OccurrencesImagesExceptionsHandler {

    @ExceptionHandler(OccurrencesImagesUploadException.class)
    public ResponseEntity<ApiResponseError> handleOccurrencesImagesUploadException(OccurrencesImagesUploadException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.BAD_GATEWAY));
    }

    @ExceptionHandler(OccurrencesImagesNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleOccurrencesImagesNotFoundException(OccurrencesImagesNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.NOT_FOUND));
    }

}
