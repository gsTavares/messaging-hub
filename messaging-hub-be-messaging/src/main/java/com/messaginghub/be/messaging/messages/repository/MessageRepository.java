package com.messaginghub.be.messaging.messages.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messaginghub.be.messaging.messages.model.Message;


public interface MessageRepository extends JpaRepository<Message, String> {
    
    List<Message> findByReceiverIdOrderByCreatedAtDesc(String receiverId);

}
