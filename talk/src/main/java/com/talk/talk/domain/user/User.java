package com.talk.talk.domain.user;

import com.talk.talk.config.utils.CommonUtils;
import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.domain.roomUser.RoomUser;
import com.talk.talk.domain.userHistory.UserHistory;
import com.talk.talk.vo.login.userInfo.UserInfoReqDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "USER")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SEQ", nullable = false)
    private Long userSeq;

    @Column(name = "ID", nullable = false, unique = true, length = 16)
    private String id;

    @Column(name = "PASSWORD",nullable = false, length = 100)
    private String password;

    @Column(name = "NAME", length = 30)
    private String name;

    @Column(name = "NICKNAME", length = 30)
    private String nickname;

    @Column(name = "PHONE", length = 11)
    private String phone;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<RoomUser> roomUsers = new ArrayList<>();

    /**
     * 정보변경
     * */
    public void updateUserInfo(UserInfoReqDto request) {
        if(!CommonUtils.isStrNullOrEmpty(request.getPassword())) this.password = request.getPassword();
        if(!CommonUtils.isStrNullOrEmpty(request.getName())) this.name = request.getName();
        if(!CommonUtils.isStrNullOrEmpty(request.getNickname())) this.nickname = request.getNickname();
        if(!CommonUtils.isStrNullOrEmpty(request.getPhone())) this.phone = request.getPhone();
        if(!CommonUtils.isStrNullOrEmpty(request.getEmail())) this.email = request.getEmail();
    }

    /**
     * 사용자 정보 내역 저장
     * */
    public UserHistory saveHistoryInfo() {
        return UserHistory.builder()
                .user(this)
                .id(this.id)
                .password(this.password)
                .name(this.name)
                .nickname(this.nickname)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
