package com.talk.talk.domain.roomUser;

import com.talk.talk.domain.room.Room;
import com.talk.talk.domain.user.User;

import java.io.Serializable;

public class RoomUserId implements Serializable {
    private Room room;
    private User user;

}
