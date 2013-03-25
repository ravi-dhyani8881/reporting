package com.ytk.utility;



import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	private static final ObjectMapper mapper = new ObjectMapper();

	public static String jsonFromObject(Object object) {
			StringWriter writer = new StringWriter();
			try {
				mapper.writeValue(writer, object);
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				logger.error("Unable to serialize to json: " + object, e);
				return null;
			}
			return writer.toString();
	}
}