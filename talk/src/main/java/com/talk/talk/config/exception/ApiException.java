package com.talk.talk.config.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiException extends RuntimeException {
    private String code;
    private String message;

    public ApiException (ExceptionEnum e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}