package com.goosemagnet.postservice.dao;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MinioPostClient implements PostClient {

    @Value("${minio.bucket}")
    private String bucket = "instagram";

    private final MinioClient client;

    public MinioPostClient(MinioClient client) {
        this.client = client;
    }

    @Override
    @SneakyThrows
    public void uploadFile(String filename, String filepath) {
        client.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucket)
                        .object(filename)
                        .filename(filepath)
                        .build()
        );
    }
}
