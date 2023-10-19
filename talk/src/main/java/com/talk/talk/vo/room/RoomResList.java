package com.talk.talk.vo.room;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResList {
    private Long roomSeq;
    private String roomTitle;
    private String lastChat;
    private String lastChattingTime;
}
