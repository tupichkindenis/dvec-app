package com.whitesoft.messaging.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public class JsonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private final static Logger logger = Logger.getLogger(JsonUtil.class);

    static {
        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Json error:" + e.getMessage());
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return null;
        }

    }
}
