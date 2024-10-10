package com.talk.talk.service.impl;

import com.talk.talk.config.exception.ServiceException;
import com.talk.talk.config.exception.ErrorType;
import com.talk.talk.config.vo.FileInfo;
import com.talk.talk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final String uploadPath;

    public FileServiceImpl(@Value("${file.upload-path}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public FileInfo upload(MultipartFile file) throws IOException {

        // 1. 파일 존재 여부
        if(file.isEmpty()) throw new ServiceException(ErrorType.COMMON_FILE_NOT_EXISTS);

        String originFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        String fileExt = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "." + fileExt;

        log.debug("originFileName : {} contentType : {} fileExt : {} uuid : {} fileName : {}", originFileName, contentType, fileExt, uuid, fileName);

        // 2. File 확장자 체크 image 로 시작하는 파일만.
        if(!contentType.startsWith("image")) throw new ServiceException(ErrorType.COMMON_FILE_NOT_AllOWED_EXTENSION);

        // 3. File 저장
        File saveFile = new File(uploadPath + fileName);
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
    public boolean remove(String fileName) {
        boolean isDelete = false;

        File file = new File(uploadPath + fileName);
        if(file.exists()) isDelete = file.delete();

        return isDelete;
    }
}
