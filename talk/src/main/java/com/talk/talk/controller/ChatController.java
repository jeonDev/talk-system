package com.talk.talk.controller;

import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/user/chat/list/{roomSeq}")
    public ApiResponse<String> selectChatList(@PathVariable("roomSeq") String roomSeq) {

        chatService.selectRoomChattingList(Long.parseLong(roomSeq), CommonUtils.getUserInfo().getUserSeq());
        return ApiResponse.<String>builder()
                .build();
    }
}
