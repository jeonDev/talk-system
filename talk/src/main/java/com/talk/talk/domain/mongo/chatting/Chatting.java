package com.talk.talk.domain.mongo.chatting;

import com.talk.talk.vo.socket.ChattingUserInfo;
import com.talk.talk.vo.type.MessageType;
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
