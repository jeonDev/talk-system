package com.talk.talk.config.jwt.security;

public class TalkAuthentication implements Authentication {

    private Object authentication;

    public TalkAuthentication(Object authentication) {
        this.authentication = authentication;
    }

    @Override
    public Object getAuthentication() {
        return this.authentication;
    }

    @Override
    public void setAuthentication(Object object) {
        this.authentication = object;
    }
}
