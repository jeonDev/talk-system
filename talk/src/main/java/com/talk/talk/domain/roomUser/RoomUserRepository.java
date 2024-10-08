package com.talk.talk.domain.roomUser;

import com.talk.talk.domain.room.Room;
import com.talk.talk.vo.room.RoomResList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomUserRepository extends JpaRepository<RoomUser, RoomUserId> {
    @Query(
            value = "SELECT ru.room.roomSeq" +
                    "  FROM RoomUser ru" +
                    " WHERE EXISTS (" +
                    "               SELECT ru_check.room.roomSeq" +
                    "                 FROM RoomUser ru_check" +
                    "                WHERE ru.room.roomSeq = ru_check.room.roomSeq" +
                    "                GROUP BY ru_check.room" +
                    "                HAVING COUNT(ru_check.room) = 2" +
                    "       )" +
                    "  AND ru.user.userSeq IN :userSeqs" +
                    " GROUP BY ru.room " +
                    "HAVING COUNT(*) = 2"
    )
    Long findByUserSeqIn(@Param("userSeqs") Long[] userSeqs);

    @Query(
            value = "SELECT new com.talk.talk.vo.room.RoomResList(" +
                    "       ru.room.roomSeq" +
                    "     , CAST(GROUP_CONCAT(ru.user.nickname) AS string )" +
                    "     , MAX(ru.room.lastChat)" +
                    "     , MAX(ru.room.lastChattingTime)" +
                    "       )" +
                    "  FROM RoomUser ru" +
                    "  JOIN ru.user u" +
                    " WHERE ru.room IN (" +
                    "                       SELECT subRu.room" +
                    "                         FROM RoomUser subRu" +
                    "                        WHERE subRu.user.userSeq = :userSeq" +
                    "                       )" +
                    "   AND ru.user.userSeq <> :userSeq" +
                    " GROUP BY ru.room" +
                    " ORDER BY MAX(ru.room.lastChattingTime) DESC nulls first" +
                    "        , ru.room.roomSeq DESC "
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
