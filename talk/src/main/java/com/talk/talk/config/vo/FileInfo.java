package com.talk.talk.config.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileInfo {
    private String fileName;
    private String filePath;
    private String fileExt;
}
