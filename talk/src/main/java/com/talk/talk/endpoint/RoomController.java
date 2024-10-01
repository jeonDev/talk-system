package com.talk.talk.endpoint;

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

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/user/room/invite")
    public ApiResponse<RoomInviteResDto> roomInvite(@RequestBody List<RoomInviteReqDto> request) {
        RoomInviteResDto result;
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();

        // 채팅인원 2명일 경우 개인 채팅방 생성으로 이동
        if(request.size() == 1) {
            result = roomService.roomInvite(request.get(0), userSeq);
        } else {
            RoomInviteReqDto dto = new RoomInviteReqDto();
            dto.setUserSeq(userSeq);
            request.add(dto);
            result = roomService.roomInvite(request);
        }

        return ApiResponse.<RoomInviteResDto>builder()
                .data(result)
                .build();
    }

    @PostMapping("/user/room/private/invite")
    public ApiResponse<RoomInviteResDto> roomPrivateInvite(@RequestBody RoomInviteReqDto request) {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();
        RoomInviteResDto result = roomService.roomInvite(request, userSeq);

        return ApiResponse.<RoomInviteResDto>builder()
                .data(result)
                .build();
    }

    @GetMapping("/user/room/list")
    public ApiResponse<List<RoomResList>> selectUserRoomList() {
        Long userSeq = CommonUtils.getUserInfo().getUserSeq();

        List<RoomResList> result = roomService.selectUserRoomList(userSeq);

        return ApiResponse.<List<RoomResList>>builder()
                .data(result)
                .build();
    }
}
