package com.vrsa9208.design.patterns.book.factory.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesUtil {

	private PropertiesUtil() {
	}

	private static final Logger LOGGER = LogManager.getLogger(PropertiesUtil.class);

	public static Properties loadProperties(String propertiesUrl) {
		try {
			Properties properties = new Properties();
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesUrl);
			properties.load(inputStream);
			return properties;
		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}
}
