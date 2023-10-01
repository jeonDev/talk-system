package com.talk.talk.domain.roomUser;

import com.talk.talk.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomUserRepository extends JpaRepository<RoomUser, RoomUserId> {
    List<RoomUser> findByRoom(Room room);

    @Query(
            value = "SELECT ru.room.roomSeq" +
                    "  FROM RoomUser ru" +
                    " WHERE ru.user.userSeq IN :userSeqs" +
                    " GROUP BY ru.room" +
                    " HAVING COUNT(ru.room) > 1"
    )
    Long findByUserSeqIn(@Param("userSeqs") Long[] userSeqs);
}
