package com.goosemagnet.authservice.service;

import com.goosemagnet.authservice.model.Credentials;
import com.goosemagnet.authservice.model.Session;
import com.goosemagnet.authservice.model.User;

import java.util.Optional;

public interface SessionService {

    Optional<User> getUserBySessionToken(String token);

    Optional<Session> createSessionUsingCredentials(Credentials credentials);
}
