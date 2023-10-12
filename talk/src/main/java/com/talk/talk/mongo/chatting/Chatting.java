package com.talk.talk.mongo.chatting;

import com.talk.talk.config.socket.vo.ChattingUserInfo;
import com.talk.talk.config.socket.vo.MessageType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("chatting")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Chatting {

    private ChattingUserInfo userInfo;
    private Long roomSeq;
    private MessageType messageType;
    private String data;
}
