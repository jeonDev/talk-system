package com.talk.talk.config.socket.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Message<T> {
    private MessageType messageType;
    private T data;
    private Long roomSeq;
}
