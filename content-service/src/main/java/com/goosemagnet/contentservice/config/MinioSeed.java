package com.goosemagnet.contentservice.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MinioSeed {

    @Value("${minio.bucket:instagram}")
    public String instagram;

    private final MinioClient client;

    @Autowired
    public MinioSeed(MinioClient client) {
        this.client = client;
    }

    @SneakyThrows
    @Bean
    public void seed() {
        BucketExistsArgs bucketExists = BucketExistsArgs.builder()
                .bucket(instagram)
                .build();

        if (!client.bucketExists(bucketExists)) {
            MakeBucketArgs makeBucket = MakeBucketArgs.builder()
                    .bucket(instagram)
                    .build();

            log.info("Creating bucket: {}", instagram);
            client.makeBucket(makeBucket);
        }

        client.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(instagram)
                        .object("no-avatar.png")
                        .filename("../images/No-Avatar.png")
                        .build()
        );
    }
}
