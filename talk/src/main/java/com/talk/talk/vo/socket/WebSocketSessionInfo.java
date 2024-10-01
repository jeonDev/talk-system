package com.talk.talk.vo.socket;

import com.talk.talk.config.jwt.vo.UserInfo;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebSocketSessionInfo {
    private WebSocketSession webSocketSession;
    private UserInfo userInfo;
}
