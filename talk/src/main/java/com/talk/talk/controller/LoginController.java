package com.talk.talk.controller;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.utils.StringUtils;
import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.config.jwt.vo.TokenInfo;
import com.talk.talk.service.UserService;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.UserDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import com.talk.talk.vo.login.signUp.SignUpResDto;
import com.talk.talk.vo.login.userInfo.UserInfoReqDto;
import com.talk.talk.vo.login.userInfo.UserInfoResDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    /**
    * 회원가입
    * */
    @PostMapping("/login/signup")
    public ApiResponse<SignUpResDto> signUp(@RequestBody @Valid SignUpReqDto request) {
        SignUpResDto result = userService.signUp(request);

        return ApiResponse.<SignUpResDto>builder()
                .data(result)
                .build();
    }

    /**
    * 로그인
    * */
    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody @Valid LoginReqDto request, HttpServletResponse response) {
        UserDto result = userService.login(request, response);
        return ApiResponse.<UserDto>builder()
                .data(result)
                .build();
    }

    /**
     * Token 재발급
     * */
    @PostMapping("/token/reIssue")
    public ApiResponse<TokenInfo> tokenReIssue(@CookieValue(value = "refreshToken", required = false) String refreshToken) throws ApiException {
        if(StringUtils.isStringEmptyOrNull(refreshToken)) throw new ApiException(ExceptionEnum.NOT_EXISTS_TOKEN);
        TokenInfo tokenInfo = userService.tokenReIssue(refreshToken);

        return ApiResponse.<TokenInfo>builder()
                .data(tokenInfo)
                .build();
    }

    /**
     * Logout
     * */
    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ApiResponse.<Void>builder().build();
    }
    /**
     * update User Info
     * */
    @PostMapping("/user/info/update")
    public ApiResponse<UserInfoResDto> userInfoUpdate(@RequestBody UserInfoReqDto request) {

        UserInfoResDto result = userService.updateUserInfo(request);

        return ApiResponse.<UserInfoResDto>builder()
                .data(result)
                .build();
    }

    /**
     * User Info Get
     * */
    @GetMapping("/user/getUserInfo")
    public ApiResponse<UserInfoResDto> getUserInfo() {
        UserInfoResDto result = userService.selectUserInfo();

        return ApiResponse.<UserInfoResDto>builder()
                .data(result)
                .build();
    }
}
