package com.talk.talk.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info("Request Filter : {} | {} | {} | {}"
                , httpRequest.getProtocol()
                , httpRequest.getMethod()
                , httpRequest.getRequestURI()
                , httpRequest.getLocalPort());
        chain.doFilter(request,response);
    }
}
