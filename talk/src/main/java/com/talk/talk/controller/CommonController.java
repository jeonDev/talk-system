package com.talk.talk.controller;

import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.CommonService;
import com.talk.talk.vo.common.file.FileUploadReqDto;
import com.talk.talk.vo.common.file.FileUploadResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

    /**
     * File Upload
     * */
    @PostMapping("/user/file/upload")
    public ApiResponse<FileUploadResDto> fileUpload(@RequestPart FileUploadReqDto request) {

        FileUploadResDto result = commonService.fileUpload(request);

        return ApiResponse.<FileUploadResDto>builder()
                .data(result)
                .build();
    }
}
