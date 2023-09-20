package com.talk.talk.domain.user;

import com.talk.talk.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
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
}
