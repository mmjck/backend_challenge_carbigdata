package com.carbigdata.ms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtils {
    public static String object2Json(Object ob){
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(ob);    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}