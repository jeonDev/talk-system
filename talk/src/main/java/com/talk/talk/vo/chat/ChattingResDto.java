package com.talk.talk.vo.chat;

import com.talk.talk.vo.socket.ChattingUserInfo;
import com.talk.talk.vo.type.MessageType;
import com.talk.talk.domain.mongo.chatting.Chatting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChattingResDto {
    private ChattingUserInfo userInfo;
    private Long roomSeq;
    private MessageType messageType;
    private String data;

    public ChattingResDto(Chatting chatting) {
        this.userInfo = chatting.getUserInfo();
        this.roomSeq = chatting.getRoomSeq();
        this.messageType = chatting.getMessageType();
        this.data = chatting.getData();
    }
}
