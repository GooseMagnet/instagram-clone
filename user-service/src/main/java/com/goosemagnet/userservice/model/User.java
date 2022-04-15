package com.goosemagnet.userservice.model;

import lombok.*;

import java.time.Instant;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class User {

    public static final String NO_DESCRIPTION = "";
    @With
    Long id;

    String email;

    String username;

    @With
    String password;

    Instant dateCreated;

    String avatarPath;

    Boolean isPrivate;

    String description;

    public User(String email, String username, String password, String avatarPath) {
        this(null, email, username, password, null, avatarPath, false, NO_DESCRIPTION);
    }

    public User(Long id, String email, String username, String password, String avatarPath) {
        this(id, email, username, password, null, avatarPath, false, NO_DESCRIPTION);
    }
}
