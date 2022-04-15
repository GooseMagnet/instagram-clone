package com.goosemagnet.postservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PostServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }
}
