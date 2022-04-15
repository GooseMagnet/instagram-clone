package com.goosemagnet.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

    @Value("${sessions.redis.host}")
    private String host;

    @Value("${sessions.redis.port}")
    private String port;

    @Bean
    public Jedis createJedis() {
        return new Jedis(host, Integer.parseInt(port));
    }
}
