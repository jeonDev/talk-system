package com.talk.talk.service;

import com.talk.talk.domain.roomUser.RoomUser;
import com.talk.talk.vo.room.RoomInviteReqDto;
import com.talk.talk.vo.room.RoomInviteResDto;
import com.talk.talk.vo.room.RoomResList;

import java.util.List;

public interface RoomService {
    RoomInviteResDto roomInvite(List<RoomInviteReqDto> request);
    RoomInviteResDto roomInvite(RoomInviteReqDto request, Long userSeq);
    List<RoomResList> selectUserRoomList(Long userSeq);
    List<RoomUser> selectRoomList(Long roomSeq);
}
