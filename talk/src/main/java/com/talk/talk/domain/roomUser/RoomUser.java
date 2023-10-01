package com.talk.talk.domain.roomUser;

import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.domain.room.Room;
import com.talk.talk.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(RoomUserId.class)
@Table(name = "ROOM_USER")
public class RoomUser extends BaseTimeEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "ROOM_SEQ")
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;
}
