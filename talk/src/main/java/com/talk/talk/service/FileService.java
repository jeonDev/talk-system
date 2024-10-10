package com.talk.talk.service;

import com.talk.talk.config.vo.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    /** File Upload */
    FileInfo upload(MultipartFile file) throws IOException;

    /** File Remove */
    boolean remove(String fileName) throws IOException;
}
