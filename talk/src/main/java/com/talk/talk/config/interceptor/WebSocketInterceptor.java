package com.talk.talk.config.interceptor;

import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.config.jwt.security.Authentication;
import com.talk.talk.config.jwt.security.TalkSecurityContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    private final GenerateJwt generateJwt;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            HttpHeaders headers = request.getHeaders();


        }
        log.info("Web Socket Interceptor : beforeHandshake");
        return super.beforeHandshake(serverHttpRequest, response, wsHandler, attributes);
    }

}
