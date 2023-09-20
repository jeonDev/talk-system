package com.talk.talk.vo.login.signUp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원가입용 Response Dto 객체
 * */
@Getter
@NoArgsConstructor
public class SignUpResDto {

    private String id;
    private String name;

    @Builder
    public SignUpResDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
