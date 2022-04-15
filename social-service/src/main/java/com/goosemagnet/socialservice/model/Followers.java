package com.goosemagnet.socialservice.model;

import lombok.Value;

@Value
public class Followers {
    Long userId;
    Long count;
}
