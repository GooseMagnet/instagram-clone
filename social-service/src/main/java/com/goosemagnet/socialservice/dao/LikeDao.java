package com.goosemagnet.socialservice.dao;

public interface LikeDao {

    int countLikesForPost(String postId);

    void likePost(String postId, long userId);

    void unlikePost(String postId, long userId);

}
