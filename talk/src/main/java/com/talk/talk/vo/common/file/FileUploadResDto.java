package com.talk.talk.vo.common.file;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileUploadResDto {
    private Long fileSeq;
    private String fileName;
//    private String filePath;
    private String fileExt;
}
