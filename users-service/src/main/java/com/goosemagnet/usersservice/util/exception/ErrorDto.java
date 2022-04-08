package com.goosemagnet.usersservice.util.exception;

import lombok.Value;

import java.time.Instant;

@Value
public class ErrorDto {
    String message;
    Integer code;
    Instant timestamp;
}
