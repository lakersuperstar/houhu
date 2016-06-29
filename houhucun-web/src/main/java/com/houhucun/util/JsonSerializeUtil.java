package com.houhucun.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;



public class JsonSerializeUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.getDeserializationConfig()
				.set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);
	}

	public static String serializeObject2Json(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException("序列化json串出错." + obj, e);
		}
	}

	public static <T> T parserJson2Object(String json, Class<T> obj) {
		try {
			return mapper.readValue(json, obj);
		} catch (Exception e) {
			throw new RuntimeException("反序列化json串出错." + json, e);
		}
	}
}
