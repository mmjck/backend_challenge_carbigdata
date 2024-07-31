package com.carbigdata.ms.presentation.exceptions;

import org.springframework.http.HttpStatus;

public record ApiResponseError(
    String message,
    HttpStatus status
) {
    
}
