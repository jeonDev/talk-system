package com.talk.talk.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    // Login Exception
    NOT_EXISTS_USER("L1", "존재하지 않는 고객입니다."),
    NOT_MATCHED_PASSWORD("L2", "패스워드가 틀렸습니다.");

    private final String code;
    private final String message;

    private ExceptionEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
