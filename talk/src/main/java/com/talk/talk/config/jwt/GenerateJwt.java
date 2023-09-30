package com.talk.talk.config.jwt;

import com.talk.talk.config.vo.TokenInfo;
import com.talk.talk.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.HttpServletRequest;
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

    private static final String BEARER_TYPE = "Bearer";
    private final Key key;
    private final Long EXPIRATION_TIME = 86400000L;
    private final Long REFRESH_EXPIRATION_TIME = 86400000L;

    @Autowired
    public GenerateJwt(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Token 생성
     * */
    public TokenInfo generateToken(User user) {

        String accessToken = generateToken("ACCESS_TOKEN", user.getUserSeq().toString());

        String refreshToken = generateToken("REFRESH_TOKEN", user.getUserSeq().toString());

        return TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .token(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Token 생성
     * */
    public String generateToken(String tokenDivisionValue, String subject) {
        long expiration = 0;
        if("REFRESH_TOKEN".equals(tokenDivisionValue)) expiration = REFRESH_EXPIRATION_TIME;
        else if ("ACCESS_TOKEN".equals(tokenDivisionValue)) expiration = EXPIRATION_TIME;

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Jwt Get Subject Info
     * */
    public String getTokenForSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith(BEARER_TYPE)) return null;

        return authorization.substring(BEARER_TYPE.toString().length() + 1, authorization.length());
    }
}
