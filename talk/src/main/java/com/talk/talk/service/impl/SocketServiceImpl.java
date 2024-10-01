package com.talk.talk.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.config.jwt.vo.UserInfo;
import com.talk.talk.vo.socket.ChattingUserInfo;
import com.talk.talk.vo.socket.Message;
import com.talk.talk.vo.type.MessageType;
import com.talk.talk.vo.socket.WebSocketSessionInfo;
import com.talk.talk.domain.commonFile.CommonFile;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.mongo.chatting.Chatting;
import com.talk.talk.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {

    private final ChatService chatService;
    private final RoomService roomService;
    private final UserService userService;
    private final CommonService commonService;
    private final GenerateJwt generateJwt;
    private final ObjectMapper objectMapper;

    /** Chatting Send Message */
    public void sendMessage(List<WebSocketSessionInfo> sessions,
                            WebSocketSession session,
                            Message<?> messageInfo,
                            String sendMsg) {
        // 1. Room User Select
        List<Long> roomUsers = roomService.selectRoomList(messageInfo.getRoomSeq())
                .stream()
                .map(r -> r.getUser().getUserSeq())
                .toList();

        // 2. Room Chatting Save (NoSQL - MongoDB)
        Chatting chatting = chatService.chattingSave(messageInfo);

        // 3. Send Msg
        sessions.stream()
                .filter(s -> roomUsers.contains(s.getUserInfo().getUserSeq()))
                .forEach(s -> {
                    try {
                        if(session.isOpen()) s.getWebSocketSession().sendMessage(new TextMessage(sendMsg));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /** Chatting Send Image */
    public void sendImage(List<WebSocketSessionInfo> sessions,
                          WebSocketSession session,
                          Message<?> messageInfo) throws JsonProcessingException {
        Long fileSeq = (Long) messageInfo.getData();
        CommonFile commonFile = commonService.selectCommonFile(fileSeq);
        messageInfo.imageDataToMessageData(commonFile.getFileName());

        String sendMsg = objectMapper.writeValueAsString(messageInfo);
        this.sendMessage(sessions,session,messageInfo,sendMsg);
    }

    /**
     * Web Socket Connect UserSeq Get
     * */
    public UserInfo getUserSeqToWebSocketSession(WebSocketSession session) {
        Map<String, String> cookie = Arrays.stream(session.getHandshakeHeaders().get("cookie").get(0).split("; "))
                .filter(s -> "refreshToken".equals(s.split("=")[0]))
                .map(item -> item.split("="))
                .collect(Collectors.toMap(item -> item[0], item -> item[1]));

        String refreshToken = cookie.get("refreshToken");
        String subject = generateJwt.getTokenForSubject(refreshToken);

        User user = userService.selectUser(Long.parseLong(subject));
        return UserInfo.builder()
                .userSeq(user.getUserSeq())
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }

    /** JSON 메시지 전송 */
    public Message<Object> messageToJsonSendMessage(List<WebSocketSessionInfo> sessions,
                                                    WebSocketSession session,
                                                    String payload) {
        UserInfo userInfo = getSessionUserInfo(sessions, session);
        JSONObject jsonObject = new JSONObject(payload);

        Object msg = null;
        String type = jsonObject.getString("type");
        MessageType messageType = getMessageType(type);

        Long roomSeq = Long.parseLong(jsonObject.getString("roomSeq"));

        if(MessageType.MESSAGE == messageType) {
            msg = jsonObject.getString("message");
        } else if (MessageType.IMAGE == messageType) {
            msg = jsonObject.getLong("message");
        }

        return Message.builder()
                .roomSeq(roomSeq)
                .data(msg)
                .userInfo(ChattingUserInfo.builder()
                        .userSeq(userInfo.getUserSeq())
                        .name(userInfo.getName())
                        .nickname(userInfo.getNickname())
                        .build())
                .messageType(messageType)
                .build();
    }

    /** 접속 사용자 정보 조회 */
    private UserInfo getSessionUserInfo(List<WebSocketSessionInfo> sessions, WebSocketSession session) {
        return sessions.stream()
                .filter(item -> session.equals(item.getWebSocketSession()))
                .findFirst()
                .get().getUserInfo();
    }

    /** 메시지 타입 체크 */
    private MessageType getMessageType(String messageType) {
        return switch (messageType) {
            case "MESSAGE" -> MessageType.MESSAGE;
            case "IMAGE" -> MessageType.IMAGE;
            default -> MessageType.MESSAGE;
        };
    }

}
