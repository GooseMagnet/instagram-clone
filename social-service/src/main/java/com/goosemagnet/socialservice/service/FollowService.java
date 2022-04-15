package com.goosemagnet.socialservice.service;

import com.goosemagnet.socialservice.model.Followers;
import com.goosemagnet.socialservice.model.Following;

public interface FollowService {

    Followers countFollowersForUser(long userId);

    Following countFollowingForUser(long userId);

    void followUser(long followee, long follower);

    void unfollowUser(long followee, long follower);
}
