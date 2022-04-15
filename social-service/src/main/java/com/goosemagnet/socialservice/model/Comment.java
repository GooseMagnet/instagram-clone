package com.goosemagnet.socialservice.model;

import lombok.*;

import java.time.Instant;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Comment {
    Long commentId;

    User user;

    @With
    String postId;

    String content;
    Instant dateCreated;
}
