package com.talk.talk.vo.friend;

import com.talk.talk.vo.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendSearchReqDto extends Page {
    private String nameOrNickname;
    private Long userSeq;
}
