package com.talk.talk.controller;

import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.config.vo.ApiResponse;
import com.talk.talk.service.RoomService;
import com.talk.talk.vo.room.RoomInviteReqDto;
import com.talk.talk.vo.room.RoomInviteResDto;
import com.talk.talk.vo.room.RoomResList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 채팅방 초대 (단체)
     * */
    @PostMapping("/user/room/invite")
    public ApiResponse<RoomInviteResDto> roomInvite(@RequestBody List<RoomInviteReqDto> request) {
        RoomInviteReqDto dto = new RoomInviteReqDto();
        dto.setUserSeq(CommonUtils.getUserInfo().getUserSeq());
        request.add(dto);
        RoomInviteResDto result = roomService.roomInvite(request);

        return ApiResponse.<RoomInviteResDto>builder()
                .data(result)
                .build();
    }

    /**
     * 채팅방 초대 (개인)
     * */
    @PostMapping("/user/room/private/invite")
    public ApiResponse<RoomInviteResDto> roomPrivateInvite(@RequestBody RoomInviteReqDto request) {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();
        RoomInviteResDto result = roomService.roomInvite(request, userSeq);

        return ApiResponse.<RoomInviteResDto>builder()
                .data(result)
                .build();
    }

    /**
     * 채팅방 조회
     * */
    @GetMapping("/user/room/list")
    public ApiResponse<List<RoomResList>> selectUserRoomList() {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();

        List<RoomResList> result = roomService.selectUserRoomList(userSeq);

        return ApiResponse.<List<RoomResList>>builder()
                .data(result)
                .build();
    }
}
