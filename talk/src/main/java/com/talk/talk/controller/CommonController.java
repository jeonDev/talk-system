package com.talk.talk.controller;

import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.CommonService;
import com.talk.talk.vo.common.file.FileUploadResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

    /**
     * File Upload
     * */
    @PostMapping("/user/file/upload")
    public ApiResponse<FileUploadResDto> fileUpload(@RequestPart MultipartFile file) {
        FileUploadResDto result = commonService.fileUpload(file);

        return ApiResponse.<FileUploadResDto>builder()
                .data(result)
                .build();
    }
}
