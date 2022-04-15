package com.goosemagnet.socialservice.service;

import com.goosemagnet.socialservice.model.Likes;

public interface LikeService {

    Likes countLikesForPost(String postId);

    void likePost(String postId, long userId);

    void unlikePost(String postId, long userId);
}
