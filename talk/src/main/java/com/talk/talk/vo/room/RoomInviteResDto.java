package com.talk.talk.vo.room;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 초대 Response VO
 * */
@Getter
@NoArgsConstructor
public class RoomInviteResDto {

    private Long roomSeq;

    @Builder
    public RoomInviteResDto(Long roomSeq) {
        this.roomSeq = roomSeq;
    }
}
