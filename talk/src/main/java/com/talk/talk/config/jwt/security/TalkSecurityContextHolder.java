package com.talk.talk.config.jwt.security;

public class TalkSecurityContextHolder {
    private static RequestContextHolder contextHolder;

    static {
        initialize();
    }

    private static void initialize() {
        contextHolder = new TalkRequestContextHolder();
    }

    public static ContextHolder getContext() {
        return contextHolder.getContextHolder();
    }
}
