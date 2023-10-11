package com.talk.talk.mongo.chatting;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChattingRepository extends MongoRepository<Chatting, Long> {
}
