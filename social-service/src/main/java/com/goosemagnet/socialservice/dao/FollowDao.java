package com.goosemagnet.socialservice.dao;

public interface FollowDao {

    long countFollowersForUser(long userId);

    void followUser(long followee, long follower);

    void unfollowUser(long followee, long follower);

    Long countFollowingForUser(long userId);
}
