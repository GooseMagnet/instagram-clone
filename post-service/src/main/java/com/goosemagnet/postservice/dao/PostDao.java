package com.goosemagnet.postservice.dao;

import com.goosemagnet.postservice.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostDao {

    Optional<Post> getPostByUserIdAndPath(long userId, String filePath);

    Page<Post> getPostsForUserWithId(long userId, Pageable pageable);

    void createPost(long userId, String filename);

    Optional<Post> getPostById(String postId);
}
