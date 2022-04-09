package com.goosemagnet.contentservice.model;

import lombok.Value;

import java.time.Instant;

@Value
public class UserUpload {
    Long userId;
    String filePath;
    Instant dateUploaded;
}
