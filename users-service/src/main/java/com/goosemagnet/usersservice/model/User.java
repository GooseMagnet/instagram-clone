package com.goosemagnet.usersservice.model;

import lombok.*;

import java.time.Instant;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class User {

    @With
    Long id;

    String email;

    String username;

    @With
    String password;

    Instant dateCreated;

    String avatarPath;

    public User(String email, String username, String password, Instant dateCreated, String avatarPath) {
        this(null, email, username, password, dateCreated, avatarPath);
    }
}
