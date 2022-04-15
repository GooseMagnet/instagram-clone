package com.goosemagnet.socialservice.controller;


import com.goosemagnet.socialservice.model.Followers;
import com.goosemagnet.socialservice.model.Following;
import com.goosemagnet.socialservice.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FollowController {

    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/users/{userId}/followers")
    public Followers countFollowersForUser(@PathVariable("userId") long userId) {
        return followService.countFollowersForUser(userId);
    }

    @GetMapping("/users/{userId}/following")
    public Following countFollowingForUser(@PathVariable("userId") long userId) {
        return followService.countFollowingForUser(userId);
    }

    @PostMapping("/users/{userId}/followers")
    public void followUser(@PathVariable("userId") long userId) {
        long currentUser = 1; // Get current user from request context
        followService.followUser(userId, currentUser);
    }

    @DeleteMapping("/users/{userId}/followers/{follower}")
    public void unfollowUser(@PathVariable("userId") long userId, @PathVariable("follower") long userId2) {
        followService.unfollowUser(userId, userId2);
    }
}
