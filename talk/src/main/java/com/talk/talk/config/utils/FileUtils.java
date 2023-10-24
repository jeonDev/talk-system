package com.talk.talk.config.utils;

import com.talk.talk.config.vo.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileUtils {
    /** File Upload */
    FileInfo upload(MultipartFile file);

    /** File Remove */
    void remove();
}
