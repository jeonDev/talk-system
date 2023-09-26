package com.talk.talk.config.jwt.security;

import java.io.Serializable;

public interface UserDetails extends Serializable {
    String getId();
}
