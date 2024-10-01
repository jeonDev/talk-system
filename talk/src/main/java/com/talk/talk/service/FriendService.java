package com.talk.talk.service;

import com.talk.talk.vo.PageResult;
import com.talk.talk.vo.friend.*;

import java.util.List;

public interface FriendService {
    PageResult<FriendSearchResDto> selectRecommendFriendList(FriendSearchReqDto request);
    List<FriendListResDto> selectFriendList(Long userSeq);
    FriendRequestResDto requestFriend(FriendRequestReqDto request);
    void deleteUserFriend(FriendRequestReqDto request);
}
