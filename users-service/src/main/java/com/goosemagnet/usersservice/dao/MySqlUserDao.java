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
import java.util.Objects;
import java.util.Optional;

@Component
public class MySqlUserDao implements UserDao {

    public static final String NO_AVATAR = "no-avatar.png";

    private static final String SELECT_ALL = "SELECT * FROM user";
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String INSERT = "INSERT INTO user (`email`, `username`, `password`, `avatar_path`, `isPrivate`, `description`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM user WHERE id = ?";
    private static final String UPDATE = "UPDATE user SET `email` = ?, `username` = ?, `password` = ?, `avatar_path` = ?, `isPrivate` = ?, `description` = ? WHERE id = ?";

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
    public Optional<User> findUserByUsername(String username) {
        return jdbcTemplate.query(SELECT_BY_USERNAME, new UserMapper(), username).stream().findFirst();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jdbcTemplate.query(SELECT_BY_EMAIL, new UserMapper(), email).stream().findFirst();
    }

    @Override
    public void create(User user) {
        String avatarPath = Objects.isNull(user.getAvatarPath()) ? NO_AVATAR : user.getAvatarPath();
        jdbcTemplate.update(INSERT, user.getEmail(), user.getUsername(), user.getPassword(), avatarPath, user.getIsPrivate(), user.getDescription());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(UPDATE, user.getEmail(), user.getUsername(), user.getPassword(), user.getAvatarPath(), user.getIsPrivate(), user.getDescription(), user.getId());
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
                    "http://localhost:9000/instagram/" + rs.getString("avatar_path"),
                    rs.getBoolean("isPrivate"),
                    rs.getString("description")
            );
        }
    }
}
