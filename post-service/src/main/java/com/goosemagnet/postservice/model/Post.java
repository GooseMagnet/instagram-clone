package com.goosemagnet.postservice.model;

import com.goosemagnet.postservice.model.User.User;
import lombok.Value;

import java.time.Instant;

@Value
public class Post {
    User user;
    String path;
    Instant dateUploaded;
}
