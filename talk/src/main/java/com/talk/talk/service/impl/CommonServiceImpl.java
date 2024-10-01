package com.talk.talk.service.impl;

import com.talk.talk.config.utils.FileUtils;
import com.talk.talk.config.vo.FileInfo;
import com.talk.talk.domain.commonFile.CommonFile;
import com.talk.talk.domain.commonFile.CommonFileRepository;
import com.talk.talk.service.CommonService;
import com.talk.talk.vo.common.file.FileUploadResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final FileUtils fileManageUtils;
    private final CommonFileRepository commonFileRepository;

    @Override
    public FileUploadResDto fileUpload(MultipartFile file) throws IOException {
        FileInfo fileInfo = fileManageUtils.upload(file);
        CommonFile commonFile = commonFileRepository.saveAndFlush(fileInfo.dtoToEntity());
        return commonFile.entityToDto();
    }

    @Override
    public CommonFile selectCommonFile(Long fileSeq) {
        return commonFileRepository.findById(fileSeq).orElse(null);
    }
}
