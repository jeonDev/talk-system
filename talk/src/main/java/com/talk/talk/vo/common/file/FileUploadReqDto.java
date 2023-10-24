package com.talk.talk.vo.common.file;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class FileUploadReqDto {
    private MultipartFile file;
}
