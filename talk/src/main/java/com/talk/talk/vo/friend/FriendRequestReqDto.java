package com.talk.talk.vo.friend;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendRequestReqDto {
    private Long myUserSeq;
    @NotNull(message = "요청 사용자 선택에 실패하였습니다.")
    private Long userSeq;
}
