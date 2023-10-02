package com.talk.talk.service;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.domain.roomUser.RoomUser;
import com.talk.talk.domain.roomUser.RoomUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final RoomUserRepository roomUserRepository;

    /** 채팅방 내용 조회 */
    public void selectRoomChattingList(Long roomSeq, Long userSeq) {
        Optional<RoomUser> roomUserOpt = roomUserRepository.findByRoomSeqAndUserSeq(roomSeq, userSeq);
        if(roomUserOpt.isEmpty()) throw new ApiException(ExceptionEnum.INVALID_ACCESS);

        return;
    }
}
