package com.talk.talk.mongo.chatting;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("chatting")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Chatting {

    private Long userSeq;
    private String name;
    private String nickname;
    private Long roomSeq;
    private String messageType;
    private String data;
}
