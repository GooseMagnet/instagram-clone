package com.goosemagnet.authservice.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Invalid username/email or password");
    }

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
