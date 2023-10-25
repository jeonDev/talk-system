package com.talk.talk.config.utils;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.vo.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class FileManageUtils implements FileUtils {

    @Value("${file.upload-path}")
    private String uploadPath;

    @Override
    public FileInfo upload(MultipartFile file) throws IOException {

        // 1. 파일 존재 여부
        if(file.isEmpty()) throw new ApiException(ExceptionEnum.COMMON_FILE_NOT_EXISTS);

        String originFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        String fileExt = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        String fileName = uploadPath + uuid + "." + fileExt;

        log.debug("originFileName : {} contentType : {} fileExt : {} uuid : {} fileName : {}", originFileName, contentType, fileExt, uuid, fileName);

        // 2. File 확장자 체크 image 로 시작하는 파일만.
        if(!contentType.startsWith("image")) throw new ApiException(ExceptionEnum.COMMON_FILE_NOT_AllOWED_EXTENSION);

        // 3. File 저장
        File saveFile = new File(fileName);
        if(!saveFile.exists()) saveFile.mkdirs();
        file.transferTo(saveFile);

        return FileInfo.builder()
                .fileName(fileName)
                .originFileName(originFileName)
                .contentType(contentType)
                .fileExt(fileExt)
                .filePath(uploadPath)
                .build();
    }

    @Override
    public void remove() {

    }
}
