package com.talk.talk.service;

import com.talk.talk.config.exception.ExceptionEnum;
import com.talk.talk.domain.room.Room;
import com.talk.talk.domain.room.RoomRepository;
import com.talk.talk.domain.roomUser.RoomUser;
import com.talk.talk.domain.roomUser.RoomUserRepository;
import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.vo.room.RoomInviteReqDto;
import com.talk.talk.vo.room.RoomInviteResDto;
import com.talk.talk.vo.room.RoomResList;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 채팅방 관리 Service
 * */
@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final static Integer ROOM_USER_MAX_SIZE = 30; // 채팅방 최대 인원 수
    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;
    private final UserRepository userRepository;

    /**
     * 채팅방 초대
     * */
    @Transactional
    public RoomInviteResDto roomInvite(List<RoomInviteReqDto> request) {

        // 최대인원 체크
        if(request.size() > ROOM_USER_MAX_SIZE) throw new IllegalArgumentException(ExceptionEnum.OVER_INVITE_SIZE.getCode());

        // 방 생성
        Room room = roomRepository.saveAndFlush(Room.builder().build());

        List<RoomUser> roomUsers = new ArrayList<RoomUser>();

        // 사용자 초대
        for(RoomInviteReqDto reqUser : request) {
            Optional<User> user = userRepository.findById(reqUser.getUserSeq());
            if(user.isEmpty()) throw new IllegalArgumentException(ExceptionEnum.NOT_EXISTS_USER.getCode());

            RoomUser roomUser = RoomUser.builder()
                    .room(room)
                    .user(user.get())
                    .build();

            roomUsers.add(roomUser);
        }

        roomUserRepository.saveAll(roomUsers);

        return RoomInviteResDto.builder()
                .roomSeq(room.getRoomSeq())
                .build();
    }

    public RoomInviteResDto roomInvite(RoomInviteReqDto request, Long userSeq) {

        // 방 존재 여부 체크
        Long[] param = {request.getUserSeq(), userSeq};
        Long roomSeq = roomUserRepository.findByUserSeqIn(param);

        if(roomSeq == null) {
            // 방 생성
            Room room = roomRepository.saveAndFlush(Room.builder().build());
            roomSeq = room.getRoomSeq().longValue();

            List<RoomUser> roomUsers = new ArrayList<RoomUser>();

            // 사용자 초대
            for (Long reqUser : param) {
                Optional<User> user = userRepository.findById(reqUser);
                if (user.isEmpty()) throw new IllegalArgumentException(ExceptionEnum.NOT_EXISTS_USER.getCode());

                RoomUser roomUser = RoomUser.builder()
                        .room(room)
                        .user(user.get())
                        .build();

                roomUsers.add(roomUser);
            }

            roomUserRepository.saveAll(roomUsers);
        }
        return RoomInviteResDto.builder()
                .roomSeq(roomSeq)
                .build();
    }

    /**
     * 채팅방 조회
     * */
    public List<RoomResList> selectUserRoomList(Long userSeq) {
        return roomUserRepository.findByRoomList(userSeq);
    }

    /**
     * 채팅방 고객 조회
     * */
    public List<RoomUser> selectRoomList(Long roomSeq) {
        return roomUserRepository.findByRoomUserList(roomSeq);
    }
}
