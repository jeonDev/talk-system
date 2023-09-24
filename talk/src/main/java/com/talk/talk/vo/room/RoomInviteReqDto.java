package com.talk.talk.vo.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 채팅방 초대 Request VO 객체
 * */
@Getter
@Setter
@ToString
public class RoomInviteReqDto {
    private Long roomSeq;
    private Long userSeq;
}
