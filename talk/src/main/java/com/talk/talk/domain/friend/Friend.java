package com.talk.talk.domain.friend;

import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.domain.user.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(FriendPk.class)
@Table(name = "FRIEND")
public class Friend extends BaseTimeEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "FRIEND_USER_SEQ")
    private User friendUser;

}
