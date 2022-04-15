package com.goosemagnet.socialservice.model;

import lombok.Value;

@Value
public class Following {
    Long userId;
    Long count;
}
