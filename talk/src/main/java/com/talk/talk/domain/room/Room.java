package com.talk.talk.domain.room;

import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.domain.roomUser.RoomUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "ROOM")
public class Room extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_SEQ", nullable = false)
    private Long roomSeq;

    @OneToMany(mappedBy = "room")
    private List<RoomUser> roomUsers = new ArrayList<>();
}
