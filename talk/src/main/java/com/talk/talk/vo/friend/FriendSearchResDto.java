package com.talk.talk.vo.friend;

import com.talk.talk.domain.user.User;
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

    public FriendSearchResDto(User user) {
        this.userSeq = user.getUserSeq();
        this.name = user.getName();
        this.nickname = user.getNickname();
    }
}
