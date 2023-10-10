package com.talk.talk.service;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.jwt.service.UserSecurityService;
import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.config.vo.TokenEnum;
import com.talk.talk.config.vo.TokenInfo;
import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.UserDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import com.talk.talk.vo.login.signUp.SignUpResDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserSecurityService userSecurityService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenerateJwt generateJwt;

    /**
     * 회원가입
     * */
    public SignUpResDto signUp(SignUpReqDto request) {

        User userEntity = User.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword())) // 패스워드 암호화
                .nickname(request.getNickname())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        userRepository.save(userEntity);

        return SignUpResDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }

    /**
     * 로그인
     * */
    public UserDto login(LoginReqDto request, HttpServletResponse response) {

        // 1. 고객 존재 여부 체크
        User user = userRepository.findById(request.getId()).orElseThrow(() ->
                new IllegalArgumentException(ExceptionEnum.NOT_EXISTS_USER.getCode())
        );

        // 2. 패스워드 체크
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IllegalArgumentException(ExceptionEnum.NOT_MATCHED_PASSWORD.getCode());

        // 3. Token 발급.
        TokenInfo token = generateJwt.generateToken(user);

        // 4. Cookie 에 Token 정보 Set
        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setMaxAge(60*60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return UserDto.builder()
                .userSeq(user.getUserSeq())
                .name(user.getName())
                .nickname(user.getNickname())
                .tokenInfo(token)
                .build();
    }

    /**
     * 토큰 재발급
     * */
    public TokenInfo tokenReIssue(String refreshToken) throws ApiException{
        String refreshTokenSubject = generateJwt.getTokenForSubject(refreshToken);

        if(refreshTokenSubject != null) {
            UserInfo userInfo = (UserInfo) userSecurityService.selectValidUserInfo(Long.parseLong(refreshTokenSubject));
            String token = generateJwt.generateToken(TokenEnum.ACCESS_TOKEN, userInfo.getUserSeq().toString());
            return TokenInfo.builder()
                    .token(token)
                    .build();
        } else {
            throw new ApiException(ExceptionEnum.NOT_EXISTS_TOKEN);
        }
    }


}
