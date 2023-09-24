package com.talk.talk.domain.user;

import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.domain.roomUser.RoomUser;
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

    @OneToMany(mappedBy = "userSeq")
    private List<RoomUser> roomUsers = new ArrayList<>();
}
