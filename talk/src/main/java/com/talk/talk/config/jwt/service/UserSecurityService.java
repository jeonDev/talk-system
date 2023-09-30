package com.talk.talk.config.jwt.service;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.jwt.security.UserDetails;
import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSecurityService {
    private final UserRepository userRepository;

    /** 인증 여부 체크 */
    public UserDetails selectValidUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_EXISTS_USER.getCode(), ExceptionEnum.NOT_EXISTS_USER.getMessage()));

        return UserInfo.builder()
                .userSeq(user.getUserSeq())
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }
}
