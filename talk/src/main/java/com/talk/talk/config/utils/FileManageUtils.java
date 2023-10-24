package com.talk.talk.config.utils;

import com.talk.talk.config.vo.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class FileManageUtils implements FileUtils {
    @Override
    public FileInfo upload(MultipartFile file) {
        return FileInfo.builder()
                .build();
    }

    @Override
    public void remove() {

    }
}
