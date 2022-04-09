package com.goosemagnet.contentservice.controller;

import com.goosemagnet.contentservice.model.UserUpload;
import com.goosemagnet.contentservice.service.ContentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users/{userId}/contents")
public class ContentController {

    private final ContentService fileService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.fileService = contentService;
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserUpload uploadFile(@PathVariable("userId") long userId, @RequestPart("file") MultipartFile file) {
        return fileService.uploadFileForUserWithId(userId, file.getBytes())
                .orElseThrow(() -> new RuntimeException("Unable to upload file"));
    }

    @GetMapping
    public Page<UserUpload> getFilesForUser(@PathVariable("userId") long userId, Pageable pageable) {
        return fileService.getFilesForUserWithId(userId, pageable);
    }
}
