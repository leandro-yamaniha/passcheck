package com.lsy.passcheck.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public final class JsonUtils {

    private final static ObjectMapper OBJECT_MAPPER = configureObjectMapperInstance();

    private JsonUtils(){

    }

    private static ObjectMapper configureObjectMapperInstance() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        return objectMapper;
    }

    public static String toString(final Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Error to convert object as string");
        }
    }



}
