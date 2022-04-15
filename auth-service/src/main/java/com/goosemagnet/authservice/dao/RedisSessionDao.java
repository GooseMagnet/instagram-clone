package com.goosemagnet.authservice.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goosemagnet.authservice.model.Session;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@Component
public class RedisSessionDao implements SessionDao {

    private final Jedis jedis;
    private final ObjectMapper objectMapper;

    public RedisSessionDao(Jedis jedis, ObjectMapper objectMapper) {
        this.jedis = jedis;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public Optional<Session> getSessionFromToken(String token) {
        return Optional.ofNullable(jedis.get(token)).map(this::parseSession);
    }

    @SneakyThrows
    @Override
    public void createSession(Session session) {
        jedis.set(session.getSessionId(), objectMapper.writeValueAsString(session));
    }

    private Session parseSession(String json) {
        try {
            return objectMapper.readValue(json, Session.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
