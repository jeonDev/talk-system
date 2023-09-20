package com.talk.talk.vo.login.signUp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원가입용 Request Dto 객체
 * */
@Getter
@Setter
@ToString
public class SignUpReqDto {

    private String id;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private String email;
}
