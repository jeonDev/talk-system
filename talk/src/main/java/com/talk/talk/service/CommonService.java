package com.talk.talk.service;

import com.talk.talk.domain.commonFile.CommonFile;
import com.talk.talk.vo.common.file.FileUploadResDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonService {
    FileUploadResDto fileUpload(MultipartFile file) throws IOException;
    CommonFile selectCommonFile(Long fileSeq);
}
