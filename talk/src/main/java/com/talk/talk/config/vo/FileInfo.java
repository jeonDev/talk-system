package com.talk.talk.config.vo;

import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.domain.commonFile.CommonFile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileInfo {
    private String fileName;
    private String originFileName;
    private String contentType;
    private String fileExt;
    private String filePath;

    public CommonFile dtoToEntity() {
        return CommonFile.builder()
                .fileName(this.fileName)
                .originFileName(this.originFileName)
                .contentType(this.contentType)
                .fileExt(this.fileExt)
                .filePath(this.filePath)
                .createUserSeq(CommonUtils.getUserInfo().getUserSeq())
                .build();
    }
}
