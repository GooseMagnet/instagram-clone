package com.goosemagnet.contentservice.config;

import com.goosemagnet.contentservice.dao.ContentClient;
import com.goosemagnet.contentservice.service.ContentService;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@Configuration
public class MinioSeed {

    @Value("${minio.bucket:instagram}")
    public String instagram;

    private final MinioClient client;
    private final ContentService service;

    @Autowired
    public MinioSeed(MinioClient client, ContentService service) {
        this.client = client;
        this.service = service;
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

        IntStream.rangeClosed(1, 100).forEach(i -> {
            IntStream.rangeClosed(1, 49).forEach(j -> {
                if (ThreadLocalRandom.current().nextFloat() < 0.05) {
                    try {
                        byte[] bytes = Files.readAllBytes(Paths.get("../images/" + j + ".jpg"));
                        service.uploadFileForUserWithId(i, bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("Done: " + i);
        });
    }
}
