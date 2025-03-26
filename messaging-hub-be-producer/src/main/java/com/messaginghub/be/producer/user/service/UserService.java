package com.messaginghub.be.producer.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.messaginghub.be.producer.user.dto.UserRequestDto;

@Service
public class UserService {
    
    @Autowired
    private KafkaTemplate<String, UserRequestDto> userKafkaTemplate;

    public void register(UserRequestDto dto) {
        userKafkaTemplate.sendDefault(dto);
    }

}
