package com.talk.talk.mongo.chatting;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "CHATTING")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Chatting {
    @Id
    private Long chattingSeq;
    private Long userSeq;
    private String name;
    private String nickname;
    private Long roomSeq;
    private String messageType;
    private String data;
}
