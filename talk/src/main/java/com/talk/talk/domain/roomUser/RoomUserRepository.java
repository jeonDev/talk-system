package com.talk.talk.domain.roomUser;

import com.talk.talk.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomUserRepository extends JpaRepository<RoomUser, RoomUserId> {
    List<RoomUser> findByRoomSeq(Room room);
}