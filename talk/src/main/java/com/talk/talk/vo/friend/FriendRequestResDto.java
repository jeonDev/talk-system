package com.talk.talk.vo.friend;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendRequestResDto {
    private Long userSeq;
    private String name;
    private String nickname;
}
