package com.talk.talk.controller;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.FriendService;
import com.talk.talk.vo.PageResult;
import com.talk.talk.vo.friend.*;
import jakarta.validation.Valid;
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
    public ApiResponse<PageResult<FriendSearchResDto>> searchFriendSearchList(FriendSearchReqDto request) {
        request.setUserSeq(CommonUtils.getUserInfo().getUserSeq());

        PageResult<FriendSearchResDto> result = friendService.selectRecommendFriendList(request);

        return ApiResponse.<PageResult<FriendSearchResDto>>builder()
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
    public ApiResponse<FriendRequestResDto> userFriendRequest(@RequestBody @Valid FriendRequestReqDto request) {

        request.setMyUserSeq(CommonUtils.getUserInfo().getUserSeq());
        FriendRequestResDto result = friendService.requestFriend(request);
        if(result == null) throw new ApiException(ExceptionEnum.SYSTEM_ERROR);

        return ApiResponse.<FriendRequestResDto>builder()
                .data(result)
                .build();
    }

    /**
     * 친구 삭제
     * */
    @PostMapping("/user/friend/remove")
    public ApiResponse<Void> userFriendRemove(@RequestBody @Valid FriendRequestReqDto request) {

        request.setMyUserSeq(CommonUtils.getUserInfo().getUserSeq());
        FriendRequestResDto result = friendService.requestFriend(request);
        friendService.deleteUserFriend(request);
        return ApiResponse.<Void>builder()
                .build();
    }

}
