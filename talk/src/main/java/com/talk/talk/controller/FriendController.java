package com.talk.talk.controller;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.domain.friend.Friend;
import com.talk.talk.service.FriendService;
import com.talk.talk.vo.friend.FriendListResDto;
import com.talk.talk.vo.friend.FriendRequestReqDto;
import com.talk.talk.vo.friend.FriendSearchReqDto;
import com.talk.talk.vo.friend.FriendSearchResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    /**
     * 사용자 조회
     * */
    @GetMapping("/user/friend/search")
    public ApiResponse<List<FriendSearchResDto>> searchFriendSearchList(FriendSearchReqDto request) {
        List<FriendSearchResDto> result = friendService.selectRecommendFriendList(request);

        return ApiResponse.<List<FriendSearchResDto>>builder()
                .data(result)
                .build();
    }

    /**
     * 친구 목록 조회
     * */
    @GetMapping("/user/friend/list")
    public ApiResponse<List<FriendListResDto>> selectFriendList() {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();

        List<FriendListResDto> result = friendService.selectFriendList(userSeq);
        return ApiResponse.<List<FriendListResDto>>builder()
                .data(result)
                .build();
    }

    /**
     * 친구 요청
     * */
    @PostMapping("/user/friend/request")
    public ApiResponse<Void> userFriendRequest(@RequestBody FriendRequestReqDto request) {

        request.setMyUserSeq(CommonUtils.getUserInfo().getUserSeq());
        Friend friend = friendService.requestFriend(request);
        if(friend == null) throw new ApiException(ExceptionEnum.SYSTEM_ERROR);

        return ApiResponse.<Void>builder()
                .build();
    }

}
