package com.goosemagnet.contentservice.dao;

import com.goosemagnet.contentservice.model.UserUpload;
import com.goosemagnet.contentservice.service.MinioFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class MySqlUserUploadDao implements UserUploadDao {

    private static final String SELECT_ALL = "SELECT user_id, file_path, date_uploaded FROM user_upload WHERE user_id = ?";
    private static final String SELECT_BY_ID_AND_PATH = "SELECT * FROM user_upload WHERE user_id = ? AND file_path = ?";
    private static final String INSERT = "INSERT INTO user_upload (`user_id`, `file_path`) VALUES (?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlUserUploadDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<UserUpload> getContentForUserWithId(long userId, Pageable pageable) {
        List<UserUpload> users = jdbcTemplate.query(SELECT_ALL + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(), new UserUploadMapper(), userId);
        return new PageImpl<>(users, pageable, users.size());
    }

    @Override
    public void persistUserUpload(long userId, String filename) {
        jdbcTemplate.update(INSERT, userId, filename);
    }

    @Override
    public Optional<UserUpload> getUserUploadByUserIdAndFilepath(long userId, String filepath) {
        return jdbcTemplate.query(SELECT_BY_ID_AND_PATH, new UserUploadMapper(), userId, filepath).stream().findFirst();
    }

    public static class UserUploadMapper implements RowMapper<UserUpload> {

        @Override
        public UserUpload mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserUpload(
                    rs.getLong("user_id"),
                    "http://localhost:9000/instagram/" + rs.getString("file_path"),
                    rs.getTimestamp("date_uploaded").toInstant()
            );
        }
    }
}
