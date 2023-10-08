package com.talk.talk.config.socket.vo;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebSocketSessionInfo {
    private WebSocketSession webSocketSession;
    private Long userSeq;
}
