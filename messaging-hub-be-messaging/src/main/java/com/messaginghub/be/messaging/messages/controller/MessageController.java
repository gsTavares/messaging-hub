package com.messaginghub.be.messaging.messages.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaginghub.be.core.util.ApiResponse;
import com.messaginghub.be.messaging.messages.dto.request.CreateMessageRequest;
import com.messaginghub.be.messaging.messages.dto.request.EditMessageRequest;
import com.messaginghub.be.messaging.messages.service.MessageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateMessageRequest createMessageRequest,
            @RequestHeader("X-User-Id") String userId) {
        messageService.create(createMessageRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Object> edit(@PathVariable String id,
            @RequestBody @Valid EditMessageRequest editMessageRequest, @RequestHeader("X-User-Id") String userId) {
        messageService.edit(id, editMessageRequest, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id, @RequestHeader("X-User-Id") String userId) {
        messageService.delete(id, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/message")
    public ResponseEntity<Object> findByReceiver(@RequestHeader("X-User-Id") String userId) {
        var messages = messageService.findByReceiver(userId);
        return ResponseEntity.ok(new ApiResponse<>(messages, null));
    }

}
