package com.talk.talk.endpoint;

import com.talk.talk.config.exception.ServiceException;
import com.talk.talk.config.exception.ErrorType;
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

    @GetMapping("/user/friend/search")
    public ApiResponse<PageResult<FriendSearchResDto>> searchFriendSearchList(FriendSearchReqDto request) {
        request.setUserSeq(CommonUtils.getUserInfo().getUserSeq());

        PageResult<FriendSearchResDto> result = friendService.selectRecommendFriendList(request);

        return ApiResponse.<PageResult<FriendSearchResDto>>builder()
                .data(result)
                .build();
    }

    @GetMapping("/user/friend/list")
    public ApiResponse<List<FriendListResDto>> selectFriendList() {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();

        List<FriendListResDto> result = friendService.selectFriendList(userSeq);
        return ApiResponse.<List<FriendListResDto>>builder()
                .data(result)
                .build();
    }

    @PostMapping("/user/friend/request")
    public ApiResponse<FriendRequestResDto> userFriendRequest(@RequestBody @Valid FriendRequestReqDto request) {

        request.setMyUserSeq(CommonUtils.getUserInfo().getUserSeq());
        FriendRequestResDto result = friendService.requestFriend(request);
        if(result == null) throw new ServiceException(ErrorType.SYSTEM_ERROR);

        return ApiResponse.<FriendRequestResDto>builder()
                .data(result)
                .build();
    }

    @PostMapping("/user/friend/remove")
    public ApiResponse<Void> userFriendRemove(@RequestBody @Valid FriendRequestReqDto request) {

        request.setMyUserSeq(CommonUtils.getUserInfo().getUserSeq());
        FriendRequestResDto result = friendService.requestFriend(request);
        friendService.deleteUserFriend(request);
        return ApiResponse.<Void>builder()
                .build();
    }

}
