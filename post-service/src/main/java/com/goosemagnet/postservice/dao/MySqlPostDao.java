package com.goosemagnet.postservice.dao;

import com.goosemagnet.postservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MySqlPostDao implements PostDao {

    private static final String SELECT_ALL_POSTS_BY_USER_ID = "SELECT user_id, post_id, date_uploaded FROM post WHERE user_id = ?";
    private static final String SELECT_BY_USER_ID_AND_POST_ID = "SELECT * FROM post WHERE user_id = ? AND post_id = ?";
    private static final String INSERT = "INSERT INTO post (`user_id`, `post_id`) VALUES (?, ?)";
    private static final String SELECT_POST_BY_ID = "SELECT user_id, post_id, date_uploaded FROM post WHERE post_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Post> getPostsForUserWithId(long userId, Pageable pageable) {
        List<Post> users = jdbcTemplate.query(SELECT_ALL_POSTS_BY_USER_ID + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(), new PostMapper(), userId);
        return new PageImpl<>(users, pageable, users.size());
    }

    @Override
    public void createPost(long userId, String filename) {
        jdbcTemplate.update(INSERT, userId, filename);
    }

    @Override
    public Optional<Post> getPostById(String postId) {
        return jdbcTemplate.query(SELECT_POST_BY_ID, new PostMapper(), postId).stream().findFirst();
    }

    @Override
    public Optional<Post> getPostByUserIdAndPath(long userId, String filepath) {
        return jdbcTemplate.query(SELECT_BY_USER_ID_AND_POST_ID, new PostMapper(), userId, filepath).stream().findFirst();
    }

    public static class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Post(
                    rs.getLong("user_id"),
                    "http://localhost:9000/instagram/" + rs.getString("post_id"),
                    rs.getTimestamp("date_uploaded").toInstant()
            );
        }
    }
}
