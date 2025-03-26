package com.messaginghub.be.producer.user.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.messaginghub.be.producer.user.dto.UserRequestDto;
import com.messaginghub.be.producer.user.serializer.UserRequestSerializer;

@Configuration
public class UserProducer {

    @Value("${kafka.url}")
    private String kafkaUrl;
    
    @Bean
    Map<String, Object> userProducerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserRequestSerializer.class);

        return props;
    }

    @Bean
    ProducerFactory<String, UserRequestDto> userProducerFactory() {
        return new DefaultKafkaProducerFactory<>(userProducerConfig());
    }

    @Bean
    KafkaTemplate<String, UserRequestDto> userKafkaTemplate() {
        var template = new KafkaTemplate<>(userProducerFactory());
        template.setDefaultTopic("userTopic");
        return template;
    }

}
