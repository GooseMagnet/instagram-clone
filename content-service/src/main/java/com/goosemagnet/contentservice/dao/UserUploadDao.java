package com.goosemagnet.contentservice.dao;

import com.goosemagnet.contentservice.model.UserUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserUploadDao {

    Optional<UserUpload> getUserUploadByUserIdAndFilepath(long userId, String filePath);

    Page<UserUpload> getContentForUserWithId(long userId, Pageable pageable);

    void persistUserUpload(long userId, String filename);
}
