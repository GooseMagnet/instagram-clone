package com.goosemagnet.userservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionInterceptor {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto resourceNotFoundException(NotFoundException ex) {
        return new ErrorDto(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                Instant.now()
        );
    }
}
