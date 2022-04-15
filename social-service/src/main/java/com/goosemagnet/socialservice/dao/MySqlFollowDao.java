package com.goosemagnet.socialservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MySqlFollowDao implements FollowDao {

    private static final String COUNT_FOLLOWERS_FOR_USER = "SELECT COUNT(*) FROM follow WHERE user_id = ?";
    private static final String FOLLOW_USER = "INSERT INTO follow VALUES (?, ?)";
    private static final String UNFOLLOW_USER = "DELETE from follow WHERE user_id = ? AND follower_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public MySqlFollowDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int countFollowersForUser(long userId) {
        return jdbcTemplate.queryForObject(COUNT_FOLLOWERS_FOR_USER, Integer.class, userId);
    }

    @Override
    public void followUser(long followee, long follower) {
        jdbcTemplate.update(FOLLOW_USER, followee, follower);
    }

    @Override
    public void unfollowUser(long followee, long follower) {
        jdbcTemplate.update(UNFOLLOW_USER, followee, follower);
    }
}
