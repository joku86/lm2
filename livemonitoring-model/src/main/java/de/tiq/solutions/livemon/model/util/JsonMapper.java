package de.tiq.solutions.livemon.model.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

	public static ObjectMapper mapper = new ObjectMapper();

	public static Object getAsJavaObject(String message, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
		Object readValue = mapper.readValue(message, clazz);
		return readValue;

	}

	public static String getAsString(Object jsonClass) throws Exception {
		return mapper.writeValueAsString(jsonClass);
	}

}
