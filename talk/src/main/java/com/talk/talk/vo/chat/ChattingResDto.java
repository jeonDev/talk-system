package com.talk.talk.vo.chat;

import com.talk.talk.config.socket.vo.ChattingUserInfo;
import com.talk.talk.mongo.chatting.Chatting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChattingResDto {
    private ChattingUserInfo userInfo;
    private Long roomSeq;
    private String messageType;
    private String data;

    public ChattingResDto(Chatting chatting) {
        this.userInfo = ChattingUserInfo.builder()
                .userSeq(chatting.getUserSeq())
                .name(chatting.getName())
                .nickname(chatting.getNickname())
                .build();
        this.roomSeq = chatting.getRoomSeq();
        this.messageType = chatting.getMessageType();
        this.data = chatting.getData();
    }
}
