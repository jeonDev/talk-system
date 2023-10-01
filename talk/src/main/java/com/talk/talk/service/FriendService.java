package com.talk.talk.service;

import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.utils.StringUtils;
import com.talk.talk.domain.friend.FriendRepository;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.vo.friend.FriendSearchReqDto;
import com.talk.talk.vo.friend.FriendSearchResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    /**
     * 친구 찾기
     */
    public List<FriendSearchResDto> selectFriendList(FriendSearchReqDto request) {
        UserInfo userInfo = CommonUtils.getUserInfo();
        List<User> list = userRepository.findByFriendSearch(userInfo.getUserSeq(), StringUtils.nvlStr(request.getNameOrNickname()));

        return list.stream()
                .map(FriendSearchResDto::new)
                .collect(Collectors.toList());
    }
}
