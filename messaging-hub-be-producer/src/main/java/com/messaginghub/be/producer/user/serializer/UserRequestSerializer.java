package com.messaginghub.be.producer.user.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messaginghub.be.producer.user.dto.UserRequestDto;

public class UserRequestSerializer implements Serializer<UserRequestDto> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, UserRequestDto data) {
        try {
            if (data == null)
                return null;
            return mapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing UserRequestDto to byte[]");
        }
    }

}
