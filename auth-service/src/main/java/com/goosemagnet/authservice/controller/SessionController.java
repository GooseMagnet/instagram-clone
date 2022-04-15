package com.goosemagnet.authservice.controller;

import com.goosemagnet.authservice.exception.UnauthorizedException;
import com.goosemagnet.authservice.model.Credentials;
import com.goosemagnet.authservice.model.Session;
import com.goosemagnet.authservice.model.User;
import com.goosemagnet.authservice.model.dto.CredentialsDto;
import com.goosemagnet.authservice.service.SessionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    public static final String X_AUTH_TOKEN = "x-auth-token";
    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public User getUserBySessionToken(@CookieValue(name = X_AUTH_TOKEN, required = false) String header) {
        return Optional.ofNullable(header)
                .flatMap(service::getUserBySessionToken)
                .orElseThrow(() -> new UnauthorizedException("Authorization Failed."));
    }

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody CredentialsDto credentialsDto) {
        validateUsernameOrEmail(credentialsDto);
        validatePassword(credentialsDto);

        Session session = mapFromDto(credentialsDto)
                .flatMap(service::createSessionUsingCredentials)
                .orElseThrow(UnauthorizedException::new);

        HttpCookie cookie = ResponseCookie.from(X_AUTH_TOKEN, session.getSessionId())
                .maxAge(Duration.ofDays(7))
                .httpOnly(true)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return new ResponseEntity<>(session, headers, HttpStatus.CREATED);
    }

    private void validateUsernameOrEmail(CredentialsDto credentialsDto) {
        if (Objects.isNull(credentialsDto) || Objects.isNull(credentialsDto.getUsernameOrEmail())) {
            throw new UnauthorizedException();
        }
    }

    private void validatePassword(CredentialsDto credentialsDto) {
        if (Objects.isNull(credentialsDto.getPassword())) {
            throw new UnauthorizedException();
        }
    }

    private Optional<Credentials> mapFromDto(CredentialsDto credentialsDto) {
        return Optional.of(new Credentials(credentialsDto.getUsernameOrEmail(), credentialsDto.getPassword()));
    }
}
