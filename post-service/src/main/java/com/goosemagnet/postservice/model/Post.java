package com.goosemagnet.postservice.model;

import lombok.Value;

import java.time.Instant;

@Value
public class Post {
    Long userId;
    String path;
    Instant dateUploaded;
}
