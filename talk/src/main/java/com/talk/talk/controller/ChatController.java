package com.talk.talk.controller;

import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.ChatService;
import com.talk.talk.vo.chat.ChattingResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/user/chat/list/{roomSeq}")
    public ApiResponse<List<ChattingResDto>> selectChatList(@PathVariable("roomSeq") String roomSeq) {

        List<ChattingResDto> result = chatService.selectRoomChattingList(Long.parseLong(roomSeq), CommonUtils.getUserInfo().getUserSeq());
        return ApiResponse.<List<ChattingResDto>>builder()
                .data(result)
                .build();
    }
}
