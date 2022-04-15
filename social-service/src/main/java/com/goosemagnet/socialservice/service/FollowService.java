package com.goosemagnet.socialservice.service;

import com.goosemagnet.socialservice.model.Followers;

public interface FollowService {

    Followers countFollowersForUser(long userId);

    void followUser(long followee, long follower);

    void unfollowUser(long followee, long follower);
}
