package com.messaginghub.be.consumer.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.messaginghub.be.consumer.user.dto.UserRequestDto;
import com.messaginghub.be.consumer.user.repository.UserRepository;
import com.messaginghub.be.core.model.User;
import com.messaginghub.be.core.model.enumerated.Role;

@Component
public class UserConsumerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @KafkaListener(topics = "userTopic", groupId = "userRegistration", containerFactory = "kafkaListenerUserContainerFactory")
    public void listenGroupUserRegistration(UserRequestDto dto) {
        User user = User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.EMPLOYEE)
                .build();

        userRepository.saveAndFlush(user);
    }

}
