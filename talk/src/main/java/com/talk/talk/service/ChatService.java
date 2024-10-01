package com.talk.talk.service;

import com.talk.talk.vo.socket.Message;
import com.talk.talk.domain.mongo.chatting.Chatting;
import com.talk.talk.vo.chat.ChattingResDto;

import java.util.List;

public interface ChatService {
    List<ChattingResDto> selectRoomChattingList(Long roomSeq, Long userSeq);
    Chatting chattingSave(Message<?> message);
}
