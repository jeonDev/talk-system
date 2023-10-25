package com.talk.talk.service;

import com.talk.talk.config.exception.ApiException;
import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.config.socket.vo.ChattingUserInfo;
import com.talk.talk.config.socket.vo.Message;
import com.talk.talk.config.socket.vo.MessageType;
import com.talk.talk.domain.room.Room;
import com.talk.talk.domain.room.RoomRepository;
import com.talk.talk.domain.roomUser.RoomUser;
import com.talk.talk.domain.roomUser.RoomUserRepository;
import com.talk.talk.mongo.chatting.Chatting;
import com.talk.talk.mongo.chatting.ChattingRepository;
import com.talk.talk.vo.chat.ChattingResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;
    private final ChattingRepository chattingRepository;

    /** 채팅방 내용 조회 */
    public List<ChattingResDto> selectRoomChattingList(Long roomSeq, Long userSeq) {
        Optional<RoomUser> roomUserOpt = roomUserRepository.findByRoomSeqAndUserSeq(roomSeq, userSeq);
        if(roomUserOpt.isEmpty()) throw new ApiException(ExceptionEnum.INVALID_ACCESS);

        return chattingRepository.findByRoomSeq(roomSeq).stream()
                .map(ChattingResDto::new)
                .toList();
    }

    /** Chatting Save */
    @Transactional
    public Chatting chattingSave(Message<?> message) {
        Chatting chatting = Chatting.builder()
                .userInfo(ChattingUserInfo.builder()
                        .userSeq(message.getUserInfo().getUserSeq())
                        .name(message.getUserInfo().getName())
                        .nickname(message.getUserInfo().getNickname())
                        .build())
                .roomSeq(message.getRoomSeq())
                .messageType(message.getMessageType())
                .data(message.getData().toString())
                .build();

        chatting = chattingRepository.save(chatting);

        String lastChat = MessageType.IMAGE == message.getMessageType() ? "Image" : chatting.getData();

        chattingLastInfoUpdate(chatting.getRoomSeq(), lastChat);

        return chatting;
    }

    private void chattingLastInfoUpdate(Long roomSeq, String chatMessage) {
        Room room = roomRepository.findById(roomSeq).get();
        room.updateLastChattingInfo(chatMessage);
        roomRepository.save(room);
    }
}
