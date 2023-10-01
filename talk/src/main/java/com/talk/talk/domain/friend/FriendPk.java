package com.talk.talk.domain.friend;

import com.talk.talk.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class FriendPk implements Serializable {

    private User user;
    private User friendUser;
}
