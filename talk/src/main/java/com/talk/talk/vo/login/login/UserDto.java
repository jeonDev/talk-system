package com.talk.talk.vo.login.login;

import com.talk.talk.config.jwt.vo.TokenInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인용 Response Dto 객체
 * */
@Getter
@NoArgsConstructor
public class UserDto {

    private Long userSeq;
    private String name;
    private String nickname;
    private TokenInfo tokenInfo;

    @Builder
    public UserDto(Long userSeq, String name, String nickname, TokenInfo tokenInfo) {
        this.userSeq = userSeq;
        this.name = name;
        this.nickname = nickname;
        this.tokenInfo = tokenInfo;
    }
}
