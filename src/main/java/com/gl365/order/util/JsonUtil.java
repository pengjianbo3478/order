package com.gl365.order.util;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.asm.TypeReference;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> T fromJsonStr(String jsonStr,Class<T> valueType){
		try {
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			return mapper.readValue(jsonStr, valueType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T fromJsonStr(String jsonStr,Class<T> valueType,Class<?> clazz){
		try {
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			JavaType type = getCollectionType(valueType, clazz);
			return mapper.readValue(jsonStr, valueType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String fromObject(Object obj){
		String resultStr = null;
		try {
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			resultStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
		}
		return resultStr;
	}
	
	
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	} 
	
	public static String toJsonString(Object object) {
		return new Gson().toJson(object);
	}
}
