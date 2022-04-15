package com.goosemagnet.socialservice.service;

import com.goosemagnet.socialservice.dao.LikeDao;
import com.goosemagnet.socialservice.model.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeDao likeDao;

    @Autowired
    public LikeServiceImpl(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    @Override
    public Likes countLikesForPost(String postId) {
        return new Likes(postId, likeDao.countLikesForPost(postId));
    }

    @Override
    public void likePost(String postId, long userId) {
        likeDao.likePost(postId, userId);
    }

    @Override
    public void unlikePost(String postId, long userId) {
        likeDao.unlikePost(postId, userId);
    }
}
