package com.goosemagnet.authservice.dao;

import com.goosemagnet.authservice.model.Session;

import java.util.Optional;

public interface SessionDao {

    Optional<Session> getSessionFromToken(String token);

    void createSession(Session session);
}
