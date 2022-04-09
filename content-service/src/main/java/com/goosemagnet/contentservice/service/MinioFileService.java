package com.goosemagnet.contentservice.service;

import com.goosemagnet.contentservice.dao.ContentClient;
import com.goosemagnet.contentservice.dao.UserUploadDao;
import com.goosemagnet.contentservice.model.UserUpload;
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
public class MinioFileService implements ContentService {

    private final ContentClient fileDao;
    private final UserUploadDao userUploadDao;

    @Autowired
    public MinioFileService(ContentClient fileDao, UserUploadDao userUploadDao) {
        this.fileDao = fileDao;
        this.userUploadDao = userUploadDao;
    }

    @Transactional
    @SneakyThrows
    @Override
    public Optional<UserUpload> uploadFileForUserWithId(long userId, byte[] bytes) {
        String filename = generateFilenameForUser(userId);
        Path path = Files.write(Path.of("/tmp/" + filename), bytes);
        return CompletableFuture
                .runAsync(() -> fileDao.uploadFile(filename, path.toAbsolutePath().toString()))
                .thenRunAsync(() -> userUploadDao.persistUserUpload(userId, filename))
                .thenApply(unused -> userUploadDao.getUserUploadByUserIdAndFilepath(userId, filename))
                .join();
    }

    @Override
    public Page<UserUpload> getFilesForUserWithId(long userId, Pageable pageable) {
        return userUploadDao.getContentForUserWithId(userId, pageable);
    }

    private static String generateFilenameForUser(long userId) {
        UUID uuid = UUID.randomUUID();
        return userId + "-" + uuid + ".png";
    }
}
