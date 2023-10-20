package com.talk.talk.domain.friend;

import com.talk.talk.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, FriendPk> {
    @Query(
            value = "SELECT f" +
                    "  FROM Friend f" +
                    "  JOIN fetch f.user" +
                    "  JOIN fetch f.friendUser" +
                    " WHERE f.user.userSeq = :userSeq"
    )
    List<Friend> findByUser(@Param("userSeq") Long userSeq);

    Optional<Friend> findByUserAndFriendUser(User user, User friendUser);
}
