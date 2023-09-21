package com.talk.talk.service;

import com.talk.talk.common.exception.ExceptionEnum;
import com.talk.talk.common.vo.TokenInfo;
import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.UserDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import com.talk.talk.vo.login.signUp.SignUpResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

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
    public UserDto login(LoginReqDto request) {

        // 1. 고객 존재 여부 체크
        User user = userRepository.findById(request.getId()).orElseThrow(() ->
                new IllegalArgumentException(ExceptionEnum.NOT_EXISTS_USER.getCode())
        );

        // 2. 패스워드 체크
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IllegalArgumentException(ExceptionEnum.NOT_MATCHED_PASSWORD.getCode());

        // 3. Token 발급.
        TokenInfo token = generateJwt.generateToken(user);

        return UserDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .tokenInfo(token)
                .build();
    }
}
