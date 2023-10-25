package com.talk.talk.config.exception;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ExceptionEnum {

    // Login Exception
    NOT_EXISTS_USER("L1", "존재하지 않는 고객입니다."),
    NOT_MATCHED_PASSWORD("L2", "패스워드가 틀렸습니다."),
    NOT_EXISTS_TOKEN("L99", "로그인 정보가 만료되었습니다."),
    USING_USER_ID("L10", "사용중인 아이디입니다."),


    // Room Exception
    OVER_INVITE_SIZE("R1", "채팅방 최대 인원을 초과하였습니다"),

    // Friend Exception
    NOT_EXISTS_FRIEND("F1", "존재하지 않는 친구입니다."),

    // Database
    DATA_DUPLICATE("500", "이미 등록된 데이터 입니다."),

    // Common
    COMMON_FILE_NOT_EXISTS("CF1", "파일이 존재하지 않습니다"),
    COMMON_FILE_NOT_AllOWED_EXTENSION("CF2", "허용되지 않은 확장자입니다."),

    // Etc
    INVALID_ACCESS("98", "비정상적인 접근입니다."),
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
