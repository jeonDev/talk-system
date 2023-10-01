package com.talk.talk.vo.friend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendRequestReqDto {
    private Long myUserSeq;
    private Long userSeq;
}
