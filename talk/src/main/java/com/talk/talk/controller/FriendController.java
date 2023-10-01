package com.talk.talk.controller;

import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.FriendService;
import com.talk.talk.vo.friend.FriendSearchReqDto;
import com.talk.talk.vo.friend.FriendSearchResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/user/friend/search")
    public ApiResponse<List<FriendSearchResDto>> searchFriendSearchList(FriendSearchReqDto request) {
        List<FriendSearchResDto> result = friendService.selectFriendList(request);

        return ApiResponse.<List<FriendSearchResDto>>builder()
                .data(result)
                .build();
    }

}
