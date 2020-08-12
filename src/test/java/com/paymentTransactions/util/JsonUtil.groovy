package com.paymentTransactions.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import spock.lang.Specification

class JsonUtil extends Specification {

    static String toJson(final Object obj){
        String jsonString = ""
        try{
            if(obj){
                ObjectMapper objectMapper = new ObjectMapper()
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                jsonString = objectMapper.writeValueAsString(obj)
            }
            jsonString
        }catch(Exception e){
            throw new RuntimeException(e)
        }
    }

    public static boolean compareJson(String actual, String expected) {

        JsonParser parser = new JsonParser()
        JsonElement actualJson = parser.parse(actual)
        JsonElement expectedJson = parser.parse(expected)

        return actualJson == expectedJson
    }
}