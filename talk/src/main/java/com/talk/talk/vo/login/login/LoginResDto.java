package com.talk.talk.vo.login.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인용 Response Dto 객체
 * */
@Getter
@NoArgsConstructor
public class LoginResDto {

    private String token;
    private String name;
    private String nickname;

    @Builder
    public LoginResDto(String token, String name, String nickname) {
        this.token = token;
        this.name = name;
        this.nickname = nickname;
    }
}
