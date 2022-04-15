package com.goosemagnet.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CredentialsDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String usernameOrEmail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    Instant dateCreated;
}
