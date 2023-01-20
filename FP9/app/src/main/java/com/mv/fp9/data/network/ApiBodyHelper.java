package com.mv.fp9.data.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.Map;

public class ApiBodyHelper {

    private static final String JSON_TOKEN = "token";

    public static String addTokenToJsonBody (String json, String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        map.put(JSON_TOKEN, token);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }

    public static Map<String, Object> convertJsonStringToMap(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

}
