package com.talk.talk.config.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talk.talk.config.socket.vo.Message;
import com.talk.talk.config.socket.vo.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private final static Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    /** Socket 접속 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sendMsg = messageToJsonSendMessage(message.getPayload());

        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(sendMsg));
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
     * JSON 메시지 전송
     * */
    private String messageToJsonSendMessage(String payload) throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject(payload);
        ObjectMapper mapper = new ObjectMapper();

        Object msg = jsonObject.getString("message");
        String messageType = jsonObject.getString("type");

        Message<Object> messageObj = Message.builder()
                .data(msg)
                .messageType(getMessageType(messageType))
                .build();

        return mapper.writeValueAsString(messageObj);
    }

    private MessageType getMessageType(String messageType) {
        switch (messageType) {
            case "MESSAGE" : return MessageType.MESSAGE;
            default: return MessageType.MESSAGE;
        }
    }

}
