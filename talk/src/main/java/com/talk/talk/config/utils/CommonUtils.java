package com.talk.talk.config.utils;

import com.talk.talk.config.jwt.security.Authentication;
import com.talk.talk.config.jwt.security.TalkSecurityContextHolder;
import com.talk.talk.config.jwt.vo.UserInfo;

public class CommonUtils {

    /** 로그인 정보 가져오기 */
    public static UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        Authentication authentication = TalkSecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthentication() instanceof UserInfo) {
            userInfo = (UserInfo) authentication.getAuthentication();
        }
        return userInfo;
    }
}
