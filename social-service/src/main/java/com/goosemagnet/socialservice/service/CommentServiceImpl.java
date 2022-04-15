package com.goosemagnet.socialservice.service;

import com.goosemagnet.socialservice.dao.CommentDao;
import com.goosemagnet.socialservice.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Page<Comment> getCommentsForPost(String postId, Pageable pageable) {
        return commentDao.getCommentsForPost(postId, pageable);
    }

    @Override
    public Comment commentOnPost(Comment comment) {
        long commentId = commentDao.commentOnPost(comment);
        return commentDao.getCommentById(commentId).orElseThrow(() -> new RuntimeException("Unable to create post"));
    }

    @Override
    public void deleteCommentOnPost(String postId, long commentId) {
        commentDao.deleteCommentOnPost(postId, commentId);
    }
}
