package com.talk.talk.vo.friend;

import com.talk.talk.domain.friend.Friend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendListResDto {
    private Long userSeq;
    private String name;
    private String nickname;

    public FriendListResDto(Friend friend) {
        this.userSeq = friend.getFriendUser().getUserSeq();
        this.name = friend.getFriendUser().getName();
        this.nickname = friend.getFriendUser().getNickname();
    }
}
