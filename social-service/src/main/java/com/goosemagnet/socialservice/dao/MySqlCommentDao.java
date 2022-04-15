package com.goosemagnet.socialservice.dao;

import com.goosemagnet.socialservice.model.Comment;
import com.goosemagnet.socialservice.model.User;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MySqlCommentDao implements CommentDao {

    private static final String SELECT_ALL = "SELECT comment.*, user.username, user.avatar_path FROM comment, user WHERE post_id = ? AND user.id = comment.user_id";
    private static final String SELECT_COMMENT_BY_ID = "SELECT * FROM comment WHERE id = ?";
    private static final String CREATE_COMMENT = "INSERT INTO comment (`user_id`, `post_id`, `content`) VALUES (?, ?, ?)";
    private static final String DELETE_COMMENT = "DELETE FROM comment WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    public MySqlCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Comment> getCommentsForPost(String postId, Pageable pageable) {
        Sort.Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Sort.Order.by("id");
        List<Comment> comments = jdbcTemplate.query(SELECT_ALL + " ORDER BY " + order.getProperty() + " " + order.getDirection().name() + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(), new CommentMapper(), postId);
        return new PageImpl<>(comments, pageable, comments.size());
    }

    @Override
    public Optional<Comment> getCommentById(long commentId) {
        return jdbcTemplate.query(SELECT_COMMENT_BY_ID, new CommentMapper(), commentId).stream().findFirst();
    }

    @Override
    public long commentOnPost(Comment comment) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(CREATE_COMMENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, comment.getUser().getId());
            preparedStatement.setString(2, comment.getPostId());
            preparedStatement.setString(3, comment.getContent());
            return preparedStatement;
        }, key);
        return Objects.requireNonNull(key.getKey()).longValue();
    }

    @Override
    public void deleteCommentOnPost(String postId, long commentId) {
        jdbcTemplate.update(DELETE_COMMENT, commentId);
    }

    private static class CommentMapper implements RowMapper<Comment> {

        @SneakyThrows
        @Override
        public Comment mapRow(ResultSet rs, int rowNum) {
            return new Comment(
                    rs.getLong("id"),
                    new User(
                            rs.getLong("user_id"),
                            rs.getString("username"),
                            "http://localhost:9000/instagram/" + rs.getString("avatar_path")
                    ),
                    rs.getString("post_id"),
                    rs.getString("content"),
                    rs.getTimestamp("date_created").toInstant()
            );
        }
    }
}
