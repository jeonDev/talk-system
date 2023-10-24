package com.talk.talk.config.utils;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.vo.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class FileManageUtils implements FileUtils {

    @Override
    public FileInfo upload(MultipartFile file) {
        // 1. 파일 존재 여부
        if(file.isEmpty()) throw new ApiException(ExceptionEnum.COMMON_FILE_NOT_EXISTS);

        String originFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        String filePath = "";

        return FileInfo.builder()
                .fileName(originFileName)
                .fileExt(contentType)
                .filePath(filePath)
                .build();
    }

    @Override
    public void remove() {

    }
}
