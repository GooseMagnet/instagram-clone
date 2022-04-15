package com.goosemagnet.socialservice.controller;


import com.goosemagnet.socialservice.model.Followers;
import com.goosemagnet.socialservice.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/followers")
public class FollowController {

    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping
    public Followers countFollowersForUser(@PathVariable("userId") long userId) {
        return followService.countFollowersForUser(userId);
    }

    @PostMapping
    public void followUser(@PathVariable("userId") long userId) {
        long currentUser = 1; // Get current user from request context
        followService.followUser(userId, currentUser);
    }

    @DeleteMapping("/{follower}")
    public void unfollowUser(@PathVariable("userId") long userId, @PathVariable("follower") long userId2) {
        followService.unfollowUser(userId, userId2);
    }
}
