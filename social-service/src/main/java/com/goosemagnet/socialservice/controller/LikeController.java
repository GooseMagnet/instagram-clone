package com.goosemagnet.socialservice.controller;

import com.goosemagnet.socialservice.model.Likes;
import com.goosemagnet.socialservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes/{postId}")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public Likes getLikesForPost(@PathVariable("postId") String postId) {
        return likeService.countLikesForPost(postId);
    }

    @PostMapping
    public void likePost(@PathVariable("postId") String postId) {
        long userId = 1; // Get current user from request context
        likeService.likePost(postId, userId);
    }

    @DeleteMapping("/{userId}")
    public void unlikePost(@PathVariable("postId") String postId, @PathVariable("userId") long userId) {
        likeService.unlikePost(postId, userId);
    }
}
