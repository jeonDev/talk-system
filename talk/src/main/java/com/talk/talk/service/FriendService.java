package com.talk.talk.service;

import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.utils.StringUtils;
import com.talk.talk.domain.friend.Friend;
import com.talk.talk.domain.friend.FriendRepository;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.vo.PageResult;
import com.talk.talk.vo.friend.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public PageResult<FriendSearchResDto> selectRecommendFriendList(FriendSearchReqDto request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPerPage());
        Page<FriendSearchResDto> result = userRepository.findByFriendSearch(request.getUserSeq(), StringUtils.nvlStr(request.getNameOrNickname()), pageable);

        return PageResult.<FriendSearchResDto>builder()
                .totalPage(result.getTotalPages())
                .data(result.getContent())
                .build();
    }

    /**
     * 친구 목록 조회
     * */
    public List<FriendListResDto> selectFriendList(Long userSeq) {

        List<Friend> list = friendRepository.findByUser(userSeq);

        return list.stream()
                .map(FriendListResDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 친구 요청
     * */
    public FriendRequestResDto requestFriend(FriendRequestReqDto request) {
        // 1. My User Check
        Optional<User> userOpt = userRepository.findById(request.getMyUserSeq());
        if(userOpt.isEmpty()) throw new IllegalArgumentException(ExceptionEnum.NOT_EXISTS_USER.getCode());

        // 2. Friend User Check
        Optional<User> requestUserOpt = userRepository.findById(request.getUserSeq());
        if(requestUserOpt.isEmpty()) throw new IllegalArgumentException(ExceptionEnum.NOT_EXISTS_USER.getCode());

        // 3. Friend Request save
        Friend friend = Friend.builder()
                .user(userOpt.get())
                .friendUser(requestUserOpt.get())
                .build();

        friend = friendRepository.save(friend);

        return FriendRequestResDto.builder()
                .userSeq(friend.getFriendUser().getUserSeq())
                .name(friend.getFriendUser().getName())
                .nickname(friend.getFriendUser().getNickname())
                .build();
    }
}
