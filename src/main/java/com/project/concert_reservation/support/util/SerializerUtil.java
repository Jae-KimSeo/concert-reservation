package com.project.concert_reservation.support.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SerializerUtil {
    private static final Logger logger = LoggerFactory.getLogger(SerializerUtil.class);

    public static <T> String serializeValue(T value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T deserializeValue(String json, Class<T> valueType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize JSON: {}", json, e);
            return null;
        }
    }
}