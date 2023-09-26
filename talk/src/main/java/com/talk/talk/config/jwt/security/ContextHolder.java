package com.talk.talk.config.jwt.security;

public interface ContextHolder {
    Authentication getAuthentication();
    void setAuthentication(Authentication authentication);
}
