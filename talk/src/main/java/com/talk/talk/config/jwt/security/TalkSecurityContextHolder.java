package com.talk.talk.config.jwt.security;

public class TalkSecurityContextHolder {
    private static RequestContextHolder contextHolder;

    public static ContextHolder getContext() {
        return contextHolder.getContextHolder();
    }
}
