package com.talk.talk.vo.login.userInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoReqDto {
    private String oldPassword;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private String email;
}