package com.goosemagnet.authservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {

    @JsonAlias("id")
    Long userId;

    @JsonAlias("avatarPath")
    String avatar;

    String username;

    String email;
    Boolean isPrivate;
}
