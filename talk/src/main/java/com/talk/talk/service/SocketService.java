package com.talk.talk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.vo.socket.Message;
import com.talk.talk.vo.socket.WebSocketSessionInfo;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface SocketService {
    void sendMessage(List<WebSocketSessionInfo> sessions,
                     WebSocketSession session,
                     Message<?> messageInfo,
                     String sendMsg);

    void sendImage(List<WebSocketSessionInfo> sessions,
                   WebSocketSession session,
                   Message<?> messageInfo) throws JsonProcessingException;

    UserInfo getUserSeqToWebSocketSession(WebSocketSession session);

    Message<Object> messageToJsonSendMessage(List<WebSocketSessionInfo> sessions,
                                             WebSocketSession session,
                                             String payload);
}
