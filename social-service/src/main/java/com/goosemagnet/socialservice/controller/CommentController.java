package com.goosemagnet.socialservice.controller;


import com.goosemagnet.socialservice.model.Comment;
import com.goosemagnet.socialservice.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public Page<Comment> getCommentsForPost(@PathVariable("postId") String postId, Pageable pageable) {
        return commentService.getCommentsForPost(postId, pageable);
    }

    @PostMapping
    public Comment commentOnPost(@PathVariable("postId") String postId, @RequestBody Comment comment) {
        return commentService.commentOnPost(comment.withPostId(postId));
    }

    @DeleteMapping("/{commentId}")
    public void unfollowUser(@PathVariable("postId") String postId, @PathVariable("commentId") long commentId) {
        commentService.deleteCommentOnPost(postId, commentId);
    }
}
