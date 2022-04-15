package com.goosemagnet.userservice.config;

import com.github.javafaker.Faker;
import com.goosemagnet.userservice.model.User;
import com.goosemagnet.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Configuration
@Slf4j
public class Seed {

    private static final String NO_AVATAR = "/no-avatar.png";
    private final UserService service;

    @Autowired
    public Seed(UserService service) {
        this.service = service;
    }

    @Bean
    public void setup() {
        Faker faker = new Faker();

        IntStream.range(0, 101).forEach(ignored -> {
            String email = faker.internet().emailAddress();
            User user = new User(
                    email,
                    email.split("@")[0],
                    faker.internet().password(8, 256),
                    faker.avatar().image()
            );
            log.info("{}", user);
            service.create(user);
        });

        service.delete(101);

        service.update(new User(1L, "goose@email.com", "goose", "password", NO_AVATAR));

        for (int i = 1; i < 708; ++i) {
            for (int j = 1; j < 50; ++j) {
                if (ThreadLocalRandom.current().nextDouble(1) < 0.2) {

                }
            }
        }
    }
}
