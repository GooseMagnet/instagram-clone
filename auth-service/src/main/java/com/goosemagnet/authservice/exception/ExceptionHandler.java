package com.goosemagnet.authservice.exception;

import com.goosemagnet.authservice.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorDto resourceNotFoundException(UnauthorizedException e) {
        return new ErrorDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), Instant.now());
    }
}
