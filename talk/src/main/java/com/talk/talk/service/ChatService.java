package com.talk.talk.service;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.socket.vo.Message;
import com.talk.talk.domain.roomUser.RoomUser;
import com.talk.talk.domain.roomUser.RoomUserRepository;
import com.talk.talk.mongo.chatting.Chatting;
import com.talk.talk.mongo.chatting.ChattingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final RoomUserRepository roomUserRepository;
    private final ChattingRepository chattingRepository;

    /** 채팅방 내용 조회 */
    public void selectRoomChattingList(Long roomSeq, Long userSeq) {
        Optional<RoomUser> roomUserOpt = roomUserRepository.findByRoomSeqAndUserSeq(roomSeq, userSeq);
        if(roomUserOpt.isEmpty()) throw new ApiException(ExceptionEnum.INVALID_ACCESS);

        return;
    }

    /** Chatting Save */
    @Transactional
    public Chatting chattingSave(Message<?> message) {
        Chatting chatting = Chatting.builder()
                .userSeq(message.getUserInfo().getUserSeq())
                .name(message.getUserInfo().getName())
                .nickname(message.getUserInfo().getNickname())
                .roomSeq(message.getRoomSeq())
                .messageType(message.getMessageType().toString())
                .data(message.getData().toString())
                .build();

        return chattingRepository.save(chatting);
    }
}
