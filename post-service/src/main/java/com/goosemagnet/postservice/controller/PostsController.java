package com.goosemagnet.postservice.controller;

import com.goosemagnet.postservice.model.Post;
import com.goosemagnet.postservice.model.PostCount;
import com.goosemagnet.postservice.service.PostService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PostsController {

    private final PostService postService;

    @Autowired
    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/users/{userId}/posts/count")
    public PostCount getNumberOfPostsForUser(@PathVariable("userId") long userId) {
        return postService.countPostsForUserWithId(userId);
    }

    @SneakyThrows
    @PostMapping(value = "/users/{userId}/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Post createPostForUserWithId(@PathVariable("userId") long userId, @RequestPart("file") MultipartFile file) {
        return postService.createPostForUserWithId(userId, file.getBytes())
                .orElseThrow(() -> new RuntimeException("Unable to create post"));
    }

    @GetMapping(value = "/users/{userId}/posts")
    public Page<Post> getPostsForUserWithId(@PathVariable("userId") long userId, Pageable pageable) {
        return postService.getPostsForUserWithId(userId, pageable);
    }

    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable("postId") String postId) {
        return postService.getPostById(postId)
                .orElseThrow(() -> new RuntimeException("Post [" + postId + "] not found"));
    }
}
