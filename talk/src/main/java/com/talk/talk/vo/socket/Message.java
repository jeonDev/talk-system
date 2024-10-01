package com.talk.talk.vo.socket;

import com.talk.talk.vo.type.MessageType;
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
    private ChattingUserInfo userInfo;

    public void imageDataToMessageData(Object data) {
        this.data = (T) data;
    }
}
