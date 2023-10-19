package com.talk.talk.config.utils;

import com.talk.talk.config.jwt.security.Authentication;
import com.talk.talk.config.jwt.security.TalkSecurityContextHolder;
import com.talk.talk.config.jwt.vo.UserInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    /** 로그인 정보 가져오기 */
    public static UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        Authentication authentication = TalkSecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getAuthentication() instanceof UserInfo) {
            userInfo = (UserInfo) authentication.getAuthentication();
        }
        return userInfo;
    }

    /** 현재 시간 가져오기 */
    public static LocalDate getNowLocalDate() {
        return LocalDate.now();
    }

    /** 현재 시간 가져오기 */
    public static LocalDateTime getNowLocalDateTime() {
        return LocalDateTime.now();
    }

    public static String getNowLocalDateTimeFormat(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return getNowLocalDateTime().format(formatter);
    }
}
