package com.messaginghub.be.messaging.messages.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.messaginghub.be.core.model.User;
import com.messaginghub.be.messaging.messages.controller.exception.InvalidDataOperationException;
import com.messaginghub.be.messaging.messages.dto.request.CreateMessageRequest;
import com.messaginghub.be.messaging.messages.dto.request.EditMessageRequest;
import com.messaginghub.be.messaging.messages.dto.response.MessageListResponse;
import com.messaginghub.be.messaging.messages.model.Message;
import com.messaginghub.be.messaging.messages.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void create(CreateMessageRequest dto, String senderId) {
        User sender = User.builder()
                .id(senderId)
                .build();

        User receiver = User.builder()
                .id(dto.receiverId())
                .build();

        Message message = Message.builder()
                .content(dto.content())
                .sender(sender)
                .receiver(receiver)
                .build();

        messageRepository.saveAndFlush(message);
    }

    public void delete(String messageId, String messageOwnerId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("Message not found"));
        messageRepository.delete(message);
    }

    public void edit(String messageId, EditMessageRequest dto, String messageOwnerId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("Message not found"));
        if (!message.getReceiver().getId().equals(messageOwnerId))
            throw new InvalidDataOperationException("Invalid operation");

        message.setContent(dto.content());
        message.setCreatedAt(LocalDateTime.now());
        messageRepository.saveAndFlush(message);
    }

    public List<MessageListResponse> findByReceiver(String receiverId) {
        return messageRepository.findByReceiverIdOrderByCreatedAtDesc(receiverId)
                .stream()
                .map(MessageListResponse::new)
                .toList();
    }

}
