package com.goosemagnet.authservice.dao;

import com.goosemagnet.authservice.model.Credentials;

import java.util.Optional;

public interface CredentialsDao {

    Optional<Credentials> getUserCredentialsById(Long userId);

    Optional<Credentials> getUserCredentialsByUsername(String username);

    Optional<Credentials> getUserCredentialsByEmail(String email);
}
