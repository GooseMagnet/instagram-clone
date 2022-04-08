package com.goosemagnet.usersservice.config;

import com.github.javafaker.Faker;
import com.goosemagnet.usersservice.dao.UserDao;
import com.goosemagnet.usersservice.model.User;
import com.goosemagnet.usersservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Configuration
@Slf4j
public class Seed {

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
                    faker.date().past(4380, TimeUnit.DAYS).toInstant(),
                    faker.avatar().image()
            );
            log.info("{}", user);
            service.create(user);
        });

        service.delete(101);

        service.update(new User(1L, "goose@email.com", "goose", "password", null, "/noavatar"));
    }
}
