package com.goosemagnet.socialservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MySqlLikeDao implements LikeDao {

    private static final String COUNT_LIKES_FOR_POST = "SELECT COUNT(*) from post_like WHERE post_id = ?";
    private static final String LIKE_POST = "INSERT INTO post_like VALUES (?, ?)";
    private static final String UNLIKE_POST = "DELETE from post_like WHERE post_id = ? AND user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlLikeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int countLikesForPost(String postId) {
        return jdbcTemplate.queryForObject(COUNT_LIKES_FOR_POST, Integer.class, postId);
    }

    @Override
    public void likePost(String postId, long userId) {
        jdbcTemplate.update(LIKE_POST, postId, userId);
    }

    @Override
    public void unlikePost(String postId, long userId) {
        jdbcTemplate.update(UNLIKE_POST, postId, userId);
    }
}
