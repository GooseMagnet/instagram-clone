package com.goosemagnet.authservice.model.dto;

import lombok.Value;

import java.time.Instant;

@Value
public class ErrorDto {
    String message;
    int code;
    Instant timestamp;
}
