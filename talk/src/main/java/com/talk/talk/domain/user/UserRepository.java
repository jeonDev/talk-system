package com.talk.talk.domain.user;

import com.talk.talk.vo.friend.FriendSearchResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    @Query(
           value = "SELECT new com.talk.talk.vo.friend.FriendSearchResDto(" +
                   "       u.userSeq" +
                   "     , u.name" +
                   "     , u.nickname" +
                   "     , f2.user.userSeq" +
                   "    )" +
                   "  FROM User u" +
                   "  LEFT JOIN fetch Friend f1 ON f1.user.userSeq = :userSeq " +
                   "   AND u.userSeq = f1.friendUser.userSeq " +
                   "  LEFT JOIN fetch Friend f2 ON f2.friendUser.userSeq = :userSeq " +
                   "   AND f2.user.userSeq = u.userSeq " +
                   " WHERE u.userSeq <> :userSeq" +
                   "   AND f1.friendUser IS NULL " +
                   "   AND (" +
                   "        u.name LIKE CONCAT('%', :nameOrNickname, '%')" +
                   "    OR  u.nickname LIKE CONCAT('%', :nameOrNickname, '%')" +
                   "        )" +
                   " ORDER BY f2.user.userSeq NULLS LAST"
    )
    Page<FriendSearchResDto> findByFriendSearch(@Param("userSeq") Long userSeq,
                                                @Param("nameOrNickname") String nameOrNickname,
                                                Pageable pageable);
}
