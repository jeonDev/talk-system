package com.talk.talk.config.filter;

import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.config.jwt.security.*;
import com.talk.talk.config.vo.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final GenerateJwt generateJwt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Preflight Request 방지
        if("OPTIONS".equals(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("Authentication Request URI : {}", request.getRequestURI());

        String accessToken = generateJwt.resolveAccessToken(request);
        if(accessToken != null && generateJwt.validDateToken(accessToken)) {
            Authentication authentication = generateJwt.getAuthentication(accessToken);
            TalkSecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
        TalkSecurityContextHolder.clear();
    }
}
