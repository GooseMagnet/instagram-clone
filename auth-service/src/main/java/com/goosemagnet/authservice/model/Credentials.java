package com.goosemagnet.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Credentials {
    Long userId;
    String usernameOrEmail;
    String password;

    String username;
    String email;

    public Credentials(String usernameOrEmail, String password) {
        this(null, usernameOrEmail, password, null, null);
    }

    public Credentials(Long userId, String username, String email, String password) {
        this(userId, null, password, username, email);
    }
}
