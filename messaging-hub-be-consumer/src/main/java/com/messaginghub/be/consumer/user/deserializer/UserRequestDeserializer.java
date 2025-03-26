package com.messaginghub.be.consumer.user.deserializer;


import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messaginghub.be.consumer.user.dto.UserRequestDto;

public class UserRequestDeserializer implements Deserializer<UserRequestDto> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserRequestDto deserialize(String topic, byte[] data) {
        try {
            if (data == null)
                return null;
            return mapper.readValue(new String(data, "UTF-8"), UserRequestDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to UserRequestDto");
        }
    }

}
