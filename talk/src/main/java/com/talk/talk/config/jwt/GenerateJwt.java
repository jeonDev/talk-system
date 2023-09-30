package com.talk.talk.config.jwt;

import com.talk.talk.config.jwt.security.Authentication;
import com.talk.talk.config.jwt.security.TalkAuthentication;
import com.talk.talk.config.jwt.security.UserDetails;
import com.talk.talk.config.jwt.service.UserSecurityService;
import com.talk.talk.config.vo.TokenEnum;
import com.talk.talk.config.vo.TokenInfo;
import com.talk.talk.domain.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SecurityException;
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
    private final Long EXPIRATION_TIME = 60 * 60 * 60L;
    private final Long REFRESH_EXPIRATION_TIME = 86400000L;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    public GenerateJwt(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Token 생성
     * */
    public TokenInfo generateToken(User user) {

        String accessToken = generateToken(TokenEnum.ACCESS_TOKEN, user.getUserSeq().toString());

        String refreshToken = generateToken(TokenEnum.REFRESH_TOKEN, user.getUserSeq().toString());

        return TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .token(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Token 생성
     * */
    public String generateToken(TokenEnum tokenInfo, String subject) {
        long expiration = 0;
        if(TokenEnum.REFRESH_TOKEN.equals(tokenInfo)) expiration = REFRESH_EXPIRATION_TIME;
        else if (TokenEnum.ACCESS_TOKEN.equals(tokenInfo)) expiration = EXPIRATION_TIME;

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

    /** 토큰 만료시간 체크 */
    public boolean validDateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch(SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch(ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch(UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch(IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    /** Authentication 객체 반환 */
    public Authentication getAuthentication(String accessToken) {
        String subject = getTokenForSubject(accessToken);
        UserDetails userDetails = userSecurityService.selectValidUserInfo(Long.parseLong(subject));
        return new TalkAuthentication(userDetails);
    }
}
