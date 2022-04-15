package com.goosemagnet.socialservice.service;

import com.goosemagnet.socialservice.dao.FollowDao;
import com.goosemagnet.socialservice.model.Followers;
import com.goosemagnet.socialservice.model.Following;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowDao followDao;

    public FollowServiceImpl(FollowDao followDao) {
        this.followDao = followDao;
    }

    @Override
    public Followers countFollowersForUser(long userId) {
        return new Followers(userId, followDao.countFollowersForUser(userId));
    }

    @Override
    public Following countFollowingForUser(long userId) {
        return new Following(userId, followDao.countFollowingForUser(userId));
    }

    @Override
    public void followUser(long followee, long follower) {
        followDao.followUser(followee, follower);
    }

    @Override
    public void unfollowUser(long followee, long follower) {
        followDao.unfollowUser(followee, follower);
    }
}
