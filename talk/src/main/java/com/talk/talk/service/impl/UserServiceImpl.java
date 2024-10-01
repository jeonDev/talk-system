package com.talk.talk.service.impl;

import com.talk.talk.config.exception.ServiceException;
import com.talk.talk.config.exception.ErrorType;
import com.talk.talk.config.jwt.service.UserSecurityService;
import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.jwt.vo.TokenEnum;
import com.talk.talk.config.jwt.vo.TokenInfo;
import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.domain.userHistory.UserHistory;
import com.talk.talk.domain.userHistory.UserHistoryRepository;
import com.talk.talk.service.UserService;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.UserDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import com.talk.talk.vo.login.signUp.SignUpResDto;
import com.talk.talk.vo.login.userInfo.UserInfoReqDto;
import com.talk.talk.vo.login.userInfo.UserInfoResDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserSecurityService userSecurityService;
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenerateJwt generateJwt;

    @Transactional
    @Override
    public SignUpResDto signUp(SignUpReqDto request) {
        // 1. ID 중복 체크
        userRepository.findById(request.getId()).ifPresent(m -> {
            throw new IllegalArgumentException(ErrorType.USING_USER_ID.getCode());
        });

        // 2. 사용자 정보 세팅
        User userEntity = User.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        userRepository.save(userEntity);

        // 3. 내역 저장
        UserHistory userHistory = userEntity.saveHistoryInfo();
        userHistoryRepository.save(userHistory);

        return SignUpResDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }

    @Transactional
    @Override
    public UserDto login(LoginReqDto request, HttpServletResponse response) {

        // 1. 고객 존재 여부 체크
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException(ErrorType.NOT_EXISTS_USER.getCode()));

        // 2. 패스워드 체크
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IllegalArgumentException(ErrorType.NOT_MATCHED_PASSWORD.getCode());

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

    @Transactional(readOnly = true)
    @Override
    public TokenInfo tokenReIssue(String refreshToken) throws ServiceException {
        String refreshTokenSubject = generateJwt.getTokenForSubject(refreshToken);

        if(refreshTokenSubject != null) {
            UserInfo userInfo = (UserInfo) userSecurityService.selectValidUserInfo(Long.parseLong(refreshTokenSubject));
            String token = generateJwt.generateToken(TokenEnum.ACCESS_TOKEN, userInfo.getUserSeq().toString());
            return TokenInfo.builder()
                    .token(token)
                    .build();
        } else {
            throw new ServiceException(ErrorType.NOT_EXISTS_TOKEN);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User selectUser(Long userSeq) {
        return userRepository.findById(userSeq).orElse(null);
    }

    @Transactional
    @Override
    public UserInfoResDto updateUserInfo(UserInfoReqDto request) {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();

        // 1. 고객 존재 여부 체크
        User user = userRepository.findById(userSeq).orElseThrow(() ->
                new IllegalArgumentException(ErrorType.NOT_EXISTS_USER.getCode())
        );

        // 2. 패스워드 정보 입력 X => 기존 패스워드 체크
        if(!CommonUtils.isStrNullOrEmpty(request.getPassword())) {
            if(passwordEncoder.matches(request.getOldPassword(), user.getPassword()) )request.setPassword(passwordEncoder.encode(request.getPassword()));
            else throw new IllegalArgumentException(ErrorType.NOT_MATCHED_PASSWORD.getCode());
        }

        // 3. 정보변경 & 저장
        user.updateUserInfo(request);

        userRepository.save(user);

        // 4. 변경내역 저장
        UserHistory userHistory = user.saveHistoryInfo();
        userHistoryRepository.save(userHistory);

        return UserInfoResDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoResDto selectMyUserInfo() {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new IllegalArgumentException(ErrorType.NOT_EXISTS_USER.getCode()));

        return UserInfoResDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }
}
