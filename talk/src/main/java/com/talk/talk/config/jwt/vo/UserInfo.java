package com.talk.talk.config.jwt.vo;

import com.talk.talk.config.jwt.security.UserDetails;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements UserDetails {

    private Long userSeq;
    private String id;
    private String name;
    private String nickname;

    @Override
    public String getId() {
        return this.id;
    }

}
