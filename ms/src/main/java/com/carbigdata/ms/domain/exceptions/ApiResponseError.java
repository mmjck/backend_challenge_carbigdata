package com.carbigdata.ms.domain.exceptions;

import org.springframework.http.HttpStatus;

public record ApiResponseError(
    String message,
    HttpStatus status
) {
    
}
