package com.talk.talk.service;

import com.talk.talk.common.exception.ExceptionEnum;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.LoginResDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import com.talk.talk.vo.login.signUp.SignUpResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     * */
    public SignUpResDto signUp(SignUpReqDto request) {
        User userEntity = User.builder()
                .id(request.getId())
                .password(request.getPassword())
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
    public LoginResDto login(LoginReqDto request) {
        Optional<User> optUser = userRepository.findById(request.getId());
        if(optUser.isEmpty()) throw new IllegalArgumentException(ExceptionEnum.NOT_EXISTS_USER.getCode());
        User user = optUser.get();
        if(user.getPassword() == null || !user.getPassword().equals(request.getPassword())) throw new IllegalArgumentException(ExceptionEnum.NOT_MATCHED_PASSWORD.getCode());

        return LoginResDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }
}
