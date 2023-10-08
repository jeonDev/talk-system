package com.talk.talk.config.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.config.socket.vo.Message;
import com.talk.talk.config.socket.vo.MessageType;
import com.talk.talk.config.socket.vo.WebSocketSessionInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final static Set<WebSocketSessionInfo> sessions = ConcurrentHashMap.newKeySet();
//    private final static Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    private final GenerateJwt generateJwt;

    /** Socket 접속 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userSeq = getUserSeqToWebSocketSession(session);
        WebSocketSessionInfo sessionInfo = WebSocketSessionInfo.builder()
                .userSeq(userSeq)
                .webSocketSession(session)
                .build();

        sessions.add(sessionInfo);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message<Object> messageInfo = messageToJsonSendMessage(message.getPayload());
        ObjectMapper mapper = new ObjectMapper();
        String sendMsg = mapper.writeValueAsString(messageInfo);


        for (WebSocketSessionInfo s : sessions) {
            s.getWebSocketSession().sendMessage(new TextMessage(sendMsg));
        }
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " Client Connection Closed : " + status);
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }



    /**
     * Web Socket Connect UserSeq Get
     * */
    private Long getUserSeqToWebSocketSession(WebSocketSession session) {
        Map<String, Object> cookie = session.getHandshakeHeaders().get("cookie")
                .stream()
                .filter(item -> "refreshToken".equals(item.split("=")[0]))
                .map(item -> item.split("="))
                .collect(Collectors.toMap(item -> item[0], item -> item[1]));
        String refreshToken = (String)cookie.get("refreshToken");
        String subject = generateJwt.getTokenForSubject(refreshToken);
        return Long.parseLong(subject);
    }

    /**
     * JSON 메시지 전송
     * */
    private Message<Object> messageToJsonSendMessage(String payload) throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject(payload);

        Object msg = jsonObject.getString("message");
        String messageType = jsonObject.getString("type");
        Long roomSeq = Long.parseLong(jsonObject.getString("roomSeq"));

        return Message.builder()
                .roomSeq(roomSeq)
                .data(msg)
                .messageType(getMessageType(messageType))
                .build();
    }

    private MessageType getMessageType(String messageType) {
        switch (messageType) {
            case "MESSAGE" : return MessageType.MESSAGE;
            default: return MessageType.MESSAGE;
        }
    }

}
