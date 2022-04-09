package com.goosemagnet.contentservice.service;

import com.goosemagnet.contentservice.model.UserUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContentService {

    Optional<UserUpload> uploadFileForUserWithId(long userId, byte[] bytes);

    Page<UserUpload> getFilesForUserWithId(long userId, Pageable pageable);

}
