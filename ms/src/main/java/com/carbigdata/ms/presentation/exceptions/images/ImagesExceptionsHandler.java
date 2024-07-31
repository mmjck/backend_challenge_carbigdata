package com.carbigdata.ms.presentation.exceptions.images;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carbigdata.ms.core.exception.images.ImagesNotFoundException;
import com.carbigdata.ms.core.exception.images.ImagesUploadException;
import com.carbigdata.ms.presentation.exceptions.ApiResponseError;

@ControllerAdvice
public class ImagesExceptionsHandler {

    @ExceptionHandler(ImagesUploadException.class)
    public ResponseEntity<ApiResponseError> handleImagesUploadException(ImagesUploadException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.BAD_GATEWAY));
    }

    @ExceptionHandler(ImagesNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleImagesNotFoundException(ImagesNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.NOT_FOUND));
    }

}
