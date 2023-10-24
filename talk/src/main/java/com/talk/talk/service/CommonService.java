package com.talk.talk.service;

import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.utils.FileUtils;
import com.talk.talk.config.vo.FileInfo;
import com.talk.talk.domain.commonFile.CommonFile;
import com.talk.talk.domain.commonFile.CommonFileRepository;
import com.talk.talk.vo.common.file.FileUploadReqDto;
import com.talk.talk.vo.common.file.FileUploadResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonService {

    private final FileUtils fileManageUtils;
    private final CommonFileRepository commonFileRepository;

    /**
     * File Upload
     * */
    public FileUploadResDto fileUpload(FileUploadReqDto request) {

        FileInfo fileInfo = fileManageUtils.upload(request.getFile());

        CommonFile commonFile = CommonFile.builder()
                .fileName(fileInfo.getFileName())
                .filePath(fileInfo.getFilePath())
                .fileExt(fileInfo.getFileExt())
                .createUserSeq(CommonUtils.getUserInfo().getUserSeq())
                .build();

        commonFileRepository.saveAndFlush(commonFile);

        return commonFile.entityToDto();
    }
}
