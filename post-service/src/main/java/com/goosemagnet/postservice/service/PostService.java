package com.goosemagnet.postservice.service;

import com.goosemagnet.postservice.model.Post;
import com.goosemagnet.postservice.model.PostCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostService {

    Optional<Post> createPostForUserWithId(long userId, byte[] bytes);

    Page<Post> getPostsForUserWithId(long userId, Pageable pageable);

    Optional<Post> getPostById(String postId);

    PostCount countPostsForUserWithId(long userId);
}
