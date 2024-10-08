package com.talk.talk.vo.socket;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ChattingUserInfo {
    private Long userSeq;
    private String name;
    private String nickname;
}
