package com.talk.talk.config.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceException extends RuntimeException {
    private String code;
    private String message;

    public ServiceException(ErrorType e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}