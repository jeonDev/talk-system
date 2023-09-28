package com.talk.talk.config.exception;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ExceptionEnum {

    // Login Exception
    NOT_EXISTS_USER("L1", "존재하지 않는 고객입니다."),
    NOT_MATCHED_PASSWORD("L2", "패스워드가 틀렸습니다."),

    // Room Exception
    OVER_INVITE_SIZE("R1", "채팅방 최대 인원을 초과하였습니다"),

    // Etc
    SYSTEM_ERROR("99", "System Error.");

    private final String code;
    private final String message;

    private ExceptionEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    /** code로 값 추출 */
    public static ExceptionEnum getMessage(String code) {
        return Arrays.stream(ExceptionEnum.values())
                .filter(exceptionEnum -> exceptionEnum.code.equals(code))
                .findAny()
                .orElse(SYSTEM_ERROR);
    }
}
