package com.talk.talk.vo.login.userInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResDto {
    private String id;
    private String name;
    private String nickname;
    private String phone;
    private String email;
}
