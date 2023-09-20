package com.talk.talk.common.vo;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiResponse<T> {
    private String status;
    private T data;
    private String message;

    public ApiResponse(T data) {
        new ApiResponse("Success", data, "정상 처리되었습니다.");
    }

    public ApiResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ApiResponse(T data, String message) {
        new ApiResponse("Success", data, message);
    }
}
