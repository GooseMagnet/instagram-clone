package com.goosemagnet.authservice.dao;

import com.goosemagnet.authservice.model.Credentials;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.Optional;

@Component
public class MySqlCredentialsDao implements CredentialsDao {
    private static final String SELECT_BY_ID = "SELECT id, username, email, password FROM user WHERE id = ?";
    private static final String SELECT_BY_USERNAME = "SELECT id, username, email, password FROM user WHERE username = ?";
    private static final String SELECT_BY_EMAIL = "SELECT id, username, email, password FROM user WHERE email = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlCredentialsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Credentials> getUserCredentialsById(Long userId) {
        return jdbcTemplate.query(SELECT_BY_ID, new CredentialsMapper(), userId).stream().findFirst();
    }

    @Override
    public Optional<Credentials> getUserCredentialsByUsername(String username) {
        return jdbcTemplate.query(SELECT_BY_USERNAME, new CredentialsMapper(), username).stream().findFirst();
    }

    @Override
    public Optional<Credentials> getUserCredentialsByEmail(String email) {
        return jdbcTemplate.query(SELECT_BY_EMAIL, new CredentialsMapper(), email).stream().findFirst();
    }

    private static class CredentialsMapper implements RowMapper<Credentials> {
        @SneakyThrows
        @Override
        public Credentials mapRow(ResultSet rs, int rowNum) {
            return new Credentials(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
            );
        }
    }
}
