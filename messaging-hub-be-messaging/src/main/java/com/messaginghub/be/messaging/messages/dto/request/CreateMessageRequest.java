package com.messaginghub.be.messaging.messages.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMessageRequest(
        @NotNull(message = "Message content is required") @NotBlank(message = "Message content can't be empty") String content,
        @NotNull(message = "Receiver is required") @NotBlank(message = "Receiver can't be empty") String receiverId) {

}
