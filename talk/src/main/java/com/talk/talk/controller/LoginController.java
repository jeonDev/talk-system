package com.talk.talk.controller;

import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.UserService;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.UserDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import com.talk.talk.vo.login.signUp.SignUpResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    /**
    * 회원가입
    * */
    @PostMapping("/login/signup")
    public ApiResponse<SignUpResDto> signUp(@RequestBody SignUpReqDto request) {
        SignUpResDto result = userService.signUp(request);

        return ApiResponse.<SignUpResDto>builder()
                .data(result)
                .build();
    }

    /**
    * 로그인
    * */
    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody LoginReqDto request) {
        UserDto result = userService.login(request);
        return ApiResponse.<UserDto>builder()
                .data(result)
                .build();
    }
}
