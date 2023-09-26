package com.talk.talk.config.jwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityAppConfig {

    @Bean
    public RequestContextHolder requestContextHolder() {
        return new TalkRequestContextHolder();
    }

    @Bean
    public ContextHolder contextHolder() {
        return new TalkContextHolder();
    }
}
