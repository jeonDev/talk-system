package com.talk.talk.domain.roomUser;

import com.talk.talk.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserRepository extends JpaRepository<RoomUser, Room> {
}
