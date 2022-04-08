package com.goosemagnet.usersservice.dao;

import com.goosemagnet.usersservice.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Component
public class MySqlUserDao implements UserDao {

    private static final String SELECT_ALL = "SELECT * FROM user";
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String INSERT = "INSERT INTO user (`email`, `username`, `password`, `avatar_path`) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM user WHERE id = ?";
    private static final String UPDATE = "UPDATE user SET `email` = ?, `username` = ?, `password` = ?, `avatar_path` = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        Sort.Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Sort.Order.by("id");
        List<User> users = jdbcTemplate.query(SELECT_ALL + " ORDER BY " + order.getProperty() + " " + order.getDirection().name() + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(), new UserMapper());
        return new PageImpl<>(users, pageable, users.size());
    }

    @Override
    public Optional<User> findUserById(long id) {
        return jdbcTemplate.query(SELECT_BY_ID, new UserMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jdbcTemplate.query(SELECT_BY_EMAIL, new UserMapper(), email).stream().findFirst();
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update(INSERT, user.getEmail(), user.getUsername(), user.getPassword(), user.getAvatarPath());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(UPDATE, user.getEmail(), user.getUsername(), user.getPassword(), user.getAvatarPath(), user.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE, id);
    }

    private static class UserMapper implements RowMapper<User> {
        @SneakyThrows
        @Override
        public User mapRow(ResultSet rs, int rowNum) {
            return new User(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getTimestamp("date_created").toInstant(),
                    rs.getString("avatar_path")
            );
        }
    }
}
