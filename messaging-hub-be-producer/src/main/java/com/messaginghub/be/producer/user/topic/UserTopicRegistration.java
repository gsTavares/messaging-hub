package com.messaginghub.be.producer.user.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserTopicRegistration {
    
    @Bean
    NewTopic userTopic() {
        return TopicBuilder.name("userTopic")
            .partitions(10)
            .replicas(3)
            .build();
    }

}
