package com.talk.talk.config.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.service.SocketService;
import com.talk.talk.vo.socket.Message;
import com.talk.talk.vo.type.MessageType;
import com.talk.talk.vo.socket.WebSocketSessionInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final static List<WebSocketSessionInfo> sessions = new ArrayList<>();
    private final SocketService socketService;
    private final ObjectMapper objectMapper;

    /** Socket 접속 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        UserInfo userInfo = socketService.getUserSeqToWebSocketSession(session);
        WebSocketSessionInfo sessionInfo = WebSocketSessionInfo.builder()
                .userInfo(userInfo)
                .webSocketSession(session)
                .build();

        sessions.add(sessionInfo);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        // 1. Message Builder
        Message<Object> messageInfo = socketService.messageToJsonSendMessage(sessions, session, message.getPayload());

        // 2. Send
        if(MessageType.MESSAGE == messageInfo.getMessageType()) {
            // 2-1. Send Message Set
            String sendMsg = objectMapper.writeValueAsString(messageInfo);
            // 2-2. Message Send
            socketService.sendMessage(sessions, session, messageInfo, sendMsg);
        } else if (MessageType.IMAGE == messageInfo.getMessageType()) {
            // 2-1. Image Message Send
            socketService.sendImage(sessions, session, messageInfo);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info(session + " Client Connection Closed : " + status);
        sessions.stream()
                .filter(item -> session.equals(item.getWebSocketSession()))
                .findFirst()
                .ifPresent(item -> sessions.remove(item));
    }
}
