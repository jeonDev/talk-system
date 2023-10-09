package com.talk.talk.domain.roomUser;

import com.talk.talk.domain.room.Room;
import com.talk.talk.vo.room.RoomResList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Query(
            value = "SELECT new com.talk.talk.vo.room.RoomResList(" +
                    "       ru.room.roomSeq" +
                    "     , CAST(GROUP_CONCAT(ru.user.nickname) AS string )" +
                    "       )" +
                    "  FROM RoomUser ru" +
                    "  JOIN ru.user u" +
                    " WHERE ru.room IN (" +
                    "                       SELECT subRu.room" +
                    "                         FROM RoomUser subRu" +
                    "                        WHERE subRu.user.userSeq = :userSeq" +
                    "                       )" +
                    "   AND ru.user.userSeq <> :userSeq" +
                    " GROUP BY ru.room"
    )
    List<RoomResList> findByRoomList(@Param("userSeq") Long userSeq);

    @Query(
            value = "SELECT ru" +
                    "  FROM RoomUser ru" +
                    " WHERE ru.room.roomSeq = :roomSeq" +
                    "   AND ru.user.userSeq = :userSeq"
    )
    Optional<RoomUser> findByRoomSeqAndUserSeq(@Param("roomSeq") Long roomSeq,
                                               @Param("userSeq") Long userSeq);

    @Query(
            value = "SELECT ru" +
                    "  FROM RoomUser ru" +
                    "  JOIN fetch ru.user" +
                    " WHERE ru.room.roomSeq = :roomSeq"
    )
    List<RoomUser> findByRoomUserList(@Param("roomSeq") Long roomSeq);
}
