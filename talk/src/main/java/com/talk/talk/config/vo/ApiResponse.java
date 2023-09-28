package com.talk.talk.config.vo;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiResponse<T> {
    private String status;
    private T data;
    private String message;


    @Builder
    public ApiResponse(String status, T data, String message) {
        this.status = status != null ? status : "SUCCESS";
        this.data = data;
        this.message = message != null ? message : "정상 처리되었습니다.";
    }
}
