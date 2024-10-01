package com.talk.talk.service;

import com.talk.talk.config.exception.ServiceException;
import com.talk.talk.config.jwt.vo.TokenInfo;
import com.talk.talk.domain.user.User;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.UserDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import com.talk.talk.vo.login.signUp.SignUpResDto;
import com.talk.talk.vo.login.userInfo.UserInfoReqDto;
import com.talk.talk.vo.login.userInfo.UserInfoResDto;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    SignUpResDto signUp(SignUpReqDto request);
    UserDto login(LoginReqDto request, HttpServletResponse response);
    TokenInfo tokenReIssue(String refreshToken) throws ServiceException;
    User selectUser(Long userSeq);
    UserInfoResDto updateUserInfo(UserInfoReqDto request);
    UserInfoResDto selectMyUserInfo();
}
