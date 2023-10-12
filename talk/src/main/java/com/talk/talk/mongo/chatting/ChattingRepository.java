package com.talk.talk.mongo.chatting;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChattingRepository extends MongoRepository<Chatting, Long> {
    List<Chatting> findByRoomSeq(Long roomSeq);
}
