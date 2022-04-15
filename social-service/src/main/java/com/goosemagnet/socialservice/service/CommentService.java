package com.goosemagnet.socialservice.service;

import com.goosemagnet.socialservice.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<Comment> getCommentsForPost(String postId, Pageable pageable);

    Comment commentOnPost(Comment comment);

    void deleteCommentOnPost(String postId, long commentId);
}
