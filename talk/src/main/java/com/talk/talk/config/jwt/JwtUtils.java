package com.talk.talk.config.jwt;

import com.talk.talk.config.jwt.security.Authentication;
import com.talk.talk.config.jwt.security.TalkAuthentication;
import com.talk.talk.config.jwt.security.TalkSecurityContextHolder;
import com.talk.talk.config.jwt.security.UserDetails;
import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {

    private final GenerateJwt generateJwt;
    private final UserService userService;

    /** 로그인 정보 가져오기 */
    public static UserInfo getUserInfo() {
        Authentication authentication = TalkSecurityContextHolder.getContext().getAuthentication();
        // TODO:
        return null;
    }

    /** Authentication 객체 반환 */
    public Authentication getAuthentication(String accessToken) {
        String subject = generateJwt.getTokenForSubject(accessToken);
        UserDetails userDetails = userService.selectValidUserInfo(subject);
        return new TalkAuthentication(userDetails);
    }
}
