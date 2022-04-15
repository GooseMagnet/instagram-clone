package com.goosemagnet.authservice.service;

import com.goosemagnet.authservice.dao.CredentialsDao;
import com.goosemagnet.authservice.dao.SessionDao;
import com.goosemagnet.authservice.exception.UnauthorizedException;
import com.goosemagnet.authservice.model.Credentials;
import com.goosemagnet.authservice.model.Session;
import com.goosemagnet.authservice.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionDao sessionDao;
    private final RestTemplate restTemplate;
    private final CredentialsDao credentialsDao;
    private final PasswordEncoder encoder;

    public SessionServiceImpl(SessionDao sessionDao, RestTemplate restTemplate, CredentialsDao credentialsDao, PasswordEncoder encoder) {
        this.sessionDao = sessionDao;
        this.restTemplate = restTemplate;
        this.credentialsDao = credentialsDao;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> getUserBySessionToken(String token) {
        Optional<Session> sessionFromToken = sessionDao.getSessionFromToken(token);
        return sessionFromToken.map(session -> restTemplate.getForEntity("http://localhost:8080/users/" + session.getUserId(), User.class).getBody());
    }

    @Override
    public Optional<Session> createSessionUsingCredentials(Credentials credentials) {
        return credentialsDao.getUserCredentialsByEmail(credentials.getUsernameOrEmail())
                .or(() -> credentialsDao.getUserCredentialsByUsername(credentials.getUsernameOrEmail()))
                .filter(dbCreds -> encoder.matches(credentials.getPassword(), dbCreds.getPassword()))
                .map(SessionServiceImpl::createSession)
                .map(newSession -> {
                    sessionDao.createSession(newSession);
                    return newSession;
                });
    }

    private static Session createSession(Credentials credentials) {
        return new Session(UUID.randomUUID().toString(), credentials.getUserId(), Instant.now());
    }
}
