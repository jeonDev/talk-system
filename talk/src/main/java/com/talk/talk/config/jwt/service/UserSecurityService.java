package com.talk.talk.config.jwt.service;

import com.talk.talk.config.exception.ServiceException;
import com.talk.talk.config.exception.ErrorType;
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
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(ErrorType.NOT_EXISTS_USER.getCode(), ErrorType.NOT_EXISTS_USER.getMessage()));
        log.info("Authentication User : {} {}", user.getUserSeq(), user.getId());

        return UserInfo.builder()
                .userSeq(user.getUserSeq())
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }
}
