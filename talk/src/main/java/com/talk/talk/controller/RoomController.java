package com.talk.talk.controller;

import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.RoomService;
import com.talk.talk.vo.room.RoomInviteReqDto;
import com.talk.talk.vo.room.RoomInviteResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 채팅방 관리 Controller
 * */
@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    /**
     * 채팅방 초대
     * */
    @PostMapping("/user/room/invite")
    public ApiResponse<RoomInviteResDto> roomInvite(@RequestBody List<RoomInviteReqDto> request) {
        // TODO: 본인 아이디 request add
        RoomInviteResDto result = roomService.roomInvite(request);

        return new ApiResponse<>(result);
    }
}
