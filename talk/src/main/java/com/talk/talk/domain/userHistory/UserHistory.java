package com.talk.talk.domain.userHistory;

import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "USER_HISTORY")
public class UserHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_SEQ", nullable = false)
    private Long historySeq;

    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @Column(name = "ID", length = 16)
    private String id;

    @Column(name = "PASSWORD", length = 100)
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
