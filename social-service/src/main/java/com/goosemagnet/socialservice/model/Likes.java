package com.goosemagnet.socialservice.model;

import lombok.Value;

@Value
public class Likes {
    String postId;
    Integer count;
}
