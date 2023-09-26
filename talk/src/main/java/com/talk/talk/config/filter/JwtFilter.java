package com.talk.talk.config.filter;

import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.config.jwt.JwtUtils;
import com.talk.talk.config.jwt.security.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final GenerateJwt generateJwt;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Request URI : ", request.getRequestURI());
        String accessToken = generateJwt.resolveAccessToken(request);
        Authentication authentication = jwtUtils.getAuthentication(accessToken);
        TalkSecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
