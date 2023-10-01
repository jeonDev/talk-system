package com.talk.talk.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    @Query(
           value = "SELECT u" +
                   "  FROM User u" +
                   " WHERE u.userSeq <> :userSeq" +
                   "   AND (" +
                   "        u.name LIKE CONCAT('%', :nameOrNickname, '%')" +
                   "    OR  u.nickname LIKE CONCAT('%', :nameOrNickname, '%')" +
                   "        )"
    )
    List<User> findByFriendSearch(@Param("userSeq") Long userSeq,
                                  @Param("nameOrNickname") String nameOrNickname);
}
