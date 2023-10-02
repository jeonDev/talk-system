package com.talk.talk.controller;

import com.talk.talk.config.vo.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/user/chat/list/{roomSeq}")
    public ApiResponse<String> selectChatList(@PathVariable("roomSeq") String roomSeq) {
        return ApiResponse.<String>builder()
                .data(roomSeq)
                .build();
    }
}
