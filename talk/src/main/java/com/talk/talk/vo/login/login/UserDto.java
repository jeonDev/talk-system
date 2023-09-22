package com.talk.talk.vo.login.login;

import com.talk.talk.config.vo.TokenInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인용 Response Dto 객체
 * */
@Getter
@NoArgsConstructor
public class UserDto {

    private String name;
    private String nickname;
    private TokenInfo tokenInfo;

    @Builder
    public UserDto(String name, String nickname, TokenInfo tokenInfo) {
        this.name = name;
        this.nickname = nickname;
        this.tokenInfo = tokenInfo;
    }
}
