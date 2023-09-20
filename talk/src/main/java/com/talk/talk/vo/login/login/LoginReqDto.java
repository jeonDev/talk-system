package com.talk.talk.vo.login.login;

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
    private String id;
    private String password;
}
