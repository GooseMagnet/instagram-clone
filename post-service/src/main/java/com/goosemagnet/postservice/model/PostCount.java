package com.goosemagnet.postservice.model;

import lombok.Value;

@Value
public class PostCount {
    Long userId;
    Long count;
}
