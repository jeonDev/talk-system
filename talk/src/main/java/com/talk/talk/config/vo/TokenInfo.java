package com.talk.talk.config.vo;

import lombok.Builder;
import lombok.Getter;

/**
 * 고객 토큰 정보
 * */
@Getter
@Builder
public class TokenInfo {

    private String grantType;
    private String token;
    private String refreshToken;
}
