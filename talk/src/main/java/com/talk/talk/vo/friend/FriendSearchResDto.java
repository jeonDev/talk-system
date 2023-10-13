package com.talk.talk.vo.friend;

import lombok.Getter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendSearchResDto {
    private Long userSeq;
    private String name;
    private String nickname;
    private Long friendUserSeq;
}
