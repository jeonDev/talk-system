package com.talk.talk.config.jwt.security;

public class TalkRequestContextHolder implements RequestContextHolder {

    private static final ThreadLocal<ContextHolder> contextHolder = new ThreadLocal<>();

    @Override
    public ContextHolder getContextHolder() {
        ContextHolder ch = contextHolder.get();
        if(ch == null) this.setContextHolder(new TalkContextHolder());
        return ch;
    }

    @Override
    public void setContextHolder(ContextHolder context) {
        contextHolder.set(context);
    }
}
