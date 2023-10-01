package com.talk.talk.room;

import com.talk.talk.domain.room.Room;
import com.talk.talk.domain.room.RoomRepository;
import com.talk.talk.domain.roomUser.RoomUser;
import com.talk.talk.domain.roomUser.RoomUserRepository;
import com.talk.talk.service.RoomService;
import com.talk.talk.vo.room.RoomInviteReqDto;
import com.talk.talk.vo.room.RoomInviteResDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoomTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomUserRepository roomUserRepository;

    @Test
    void 채팅방_생성() {
        // given
        List<RoomInviteReqDto> request = new ArrayList<>();
        RoomInviteReqDto reqUser1 = new RoomInviteReqDto();
        reqUser1.setUserSeq(1L);
        request.add(reqUser1);
        RoomInviteReqDto reqUser2 = new RoomInviteReqDto();
        reqUser2.setUserSeq(2L);
        request.add(reqUser2);
        // when
        RoomInviteResDto response = roomService.roomInvite(request);

        // then
        Room room = roomRepository.findById(response.getRoomSeq()).get();
        List<RoomUser> roomUsers = roomUserRepository.findByRoom(room);
        assertThat(roomUsers.size()).isEqualTo(request.size());
    }
}
