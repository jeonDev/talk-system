package com.talk.talk.config.filter;

import com.talk.talk.config.jwt.GenerateJwt;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Request URI : ", request.getRequestURI());
        String accessToken = generateJwt.resolveAccessToken(request);
        if(accessToken != null && generateJwt.validDateToken(accessToken)) {
            Authentication authentication = generateJwt.getAuthentication(accessToken);
            TalkSecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
