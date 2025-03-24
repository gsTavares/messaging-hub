package com.messaginghub.be.messaging.messages.dto.response;

import java.time.LocalDateTime;

import com.messaginghub.be.messaging.messages.model.Message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageListResponse {
    
    private String id;
    private String content;
    private String senderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MessageListResponse(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.senderId = message.getSender().getId();
        this.createdAt = message.getCreatedAt();
        this.updatedAt = message.getUpdatedAt();
    }

}
