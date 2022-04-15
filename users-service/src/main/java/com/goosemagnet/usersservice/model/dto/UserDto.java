package com.goosemagnet.usersservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goosemagnet.usersservice.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    String username;
    Instant dateCreated;
    String avatarPath;

    Boolean isPrivate;

    String description;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.dateCreated = user.getDateCreated();
        this.avatarPath = user.getAvatarPath();
        this.isPrivate = user.getIsPrivate();
        this.description = user.getDescription();
    }
}
