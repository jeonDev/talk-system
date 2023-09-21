package com.talk.talk.config.jwt;

import com.talk.talk.common.vo.TokenInfo;
import com.talk.talk.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;


import java.security.Key;
import java.util.Date;

/**
 * JWT 생성
 * */
@Slf4j
@Component
public class GenerateJwt {

    private final Key key;
    private final Long EXPIRATION_TIME = 86400000L;

    @Autowired
    public GenerateJwt(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Token 생성
     * */
    public TokenInfo generateToken(User user) {

        String accessToken = Jwts.builder()
                .setSubject(user.getUserSeq().toString())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .token(accessToken)
                .build();
    }
}
