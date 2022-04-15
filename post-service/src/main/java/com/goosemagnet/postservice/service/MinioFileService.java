package com.goosemagnet.postservice.service;

import com.goosemagnet.postservice.dao.PostClient;
import com.goosemagnet.postservice.dao.PostDao;
import com.goosemagnet.postservice.model.Post;
import com.goosemagnet.postservice.model.PostCount;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class MinioFileService implements PostService {

    private final PostClient fileDao;
    private final PostDao postDao;

    @Autowired
    public MinioFileService(PostClient fileDao, PostDao postDao) {
        this.fileDao = fileDao;
        this.postDao = postDao;
    }

    @Transactional
    @SneakyThrows
    @Override
    public Optional<Post> createPostForUserWithId(long userId, byte[] bytes) {
        String filename = generateFilenameForUser(userId);
        Path path = Files.write(Path.of("/tmp/" + filename), bytes);
        return CompletableFuture
                .runAsync(() -> fileDao.uploadFile(filename, path.toAbsolutePath().toString()))
                .thenRunAsync(() -> postDao.createPost(userId, filename))
                .thenApply(unused -> postDao.getPostByUserIdAndPath(userId, filename))
                .join();
    }

    @Override
    public Page<Post> getPostsForUserWithId(long userId, Pageable pageable) {
        return postDao.getPostsForUserWithId(userId, pageable);
    }

    @Override
    public Optional<Post> getPostById(String postId) {
        return postDao.getPostById(postId + ".png");
    }

    @Override
    public PostCount countPostsForUserWithId(long userId) {
        return new PostCount(userId, postDao.countPostsForUserWithId(userId));
    }

    private static String generateFilenameForUser(long userId) {
        UUID uuid = UUID.randomUUID();
        return userId + "-" + uuid + ".png";
    }
}
