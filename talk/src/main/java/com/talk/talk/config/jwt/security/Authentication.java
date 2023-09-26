package com.talk.talk.config.jwt.security;

/**
 * 인증용객체
 * */
public interface Authentication {

    Object getAuthentication();

    void setAuthentication(Object object);
}
