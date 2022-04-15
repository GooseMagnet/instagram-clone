package com.goosemagnet.socialservice.dao;

import com.goosemagnet.socialservice.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentDao {

    Page<Comment> getCommentsForPost(String postId, Pageable pageable);

    Optional<Comment> getCommentById(long commentId);

    long commentOnPost(Comment comment);

    void deleteCommentOnPost(String postId, long commentId);
}
