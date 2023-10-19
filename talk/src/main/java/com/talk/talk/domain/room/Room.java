package com.talk.talk.domain.room;

import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.domain.roomUser.RoomUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "ROOM")
public class Room extends BaseTimeEntity {

    /**
     * 방 순번
     * */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_SEQ", nullable = false)
    private Long roomSeq;

    /**
     * 마지막 채팅
     */
    @Column(name = "LAST_CHAT")
    private String lastChat;

    /**
     * 마지막 대화 시간
     */
    @Column(name = "LAST_CHATTING_TIME")
    private String lastChattingTime;

    /**
     * 채팅방 고객 정보
     */
    @OneToMany(mappedBy = "room")
    private List<RoomUser> roomUsers = new ArrayList<>();

    public void updateLastChattingInfo(String lastChat) {
        this.lastChat = lastChat;
        this.lastChattingTime = new Date().toString();
    }

}
