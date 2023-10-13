package com.talk.talk.vo.login.login;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 로그인 Request Dto 객체
 * */
@Getter
@Setter
@ToString
public class LoginReqDto {

    /**
     * 아이디
     */
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "아이디는 영문 & 숫자 4~16자 사이로 입력 해 주세요.")
    private String id;

    /**
     * 패스워드
     */
    @Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "비밀번호는 영문 & 숫자 8~20자 사이로 입력 해 주세요.")
    private String password;
}
