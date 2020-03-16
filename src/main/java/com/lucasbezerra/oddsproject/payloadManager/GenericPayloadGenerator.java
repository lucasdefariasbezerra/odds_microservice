package com.lucasbezerra.oddsproject.payloadManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GenericPayloadGenerator {
    private ObjectNode objectNode;

    private GenericPayloadGenerator() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectNode = objectMapper.createObjectNode();
    }

    private static class SingletonHelper {
        private static final GenericPayloadGenerator INSTANCE = new GenericPayloadGenerator();
    }

    public static GenericPayloadGenerator getInstance() {
        return SingletonHelper.INSTANCE;
    }


    public ObjectNode buildResponseMessage(String fieldName, String value) {
        objectNode.put(fieldName, value);
        return objectNode;
    }
}
