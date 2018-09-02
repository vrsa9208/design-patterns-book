package com.vrsa9208.design.patterns.book.factory.dao.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vrsa9208.design.patterns.book.factory.util.PropertiesUtil;

public class PostgreSQLDBAdapter implements IDBAdapter {
	
	private static final Logger LOGGER = LogManager.getLogger(PostgreSQLDBAdapter.class);

	private static final String DB_PROPERTIES = "factory/DBPostgreSQL.properties";
	private static final String DB_NAME_PROP = "dbname";
	private static final String DB_HOST_PROP = "host";
	private static final String DB_PASSWORD_PROP = "password";
	private static final String DB_PORT_PROP = "port";
	private static final String DB_USER_PROP = "user";
	private static final String DB_DRIVER_PROP = "driver";

	public Connection getConnection() {
		Connection connection = null;
		try {
			Properties properties = PropertiesUtil.loadProperties(DB_PROPERTIES);
			String user = properties.getProperty(DB_USER_PROP);
			String password = properties.getProperty(DB_PASSWORD_PROP);
			String driver = properties.getProperty(DB_DRIVER_PROP);

			Class.forName(driver);
			connection = DriverManager.getConnection(createConnectionString(properties), user, password);
		} catch (SQLException | ClassNotFoundException ex) {
			LOGGER.error(ex);
		}

		return connection;
	}

	private String createConnectionString(Properties properties) {
		String host = properties.getProperty(DB_HOST_PROP);
		String port = properties.getProperty(DB_PORT_PROP);
		String dbName = properties.getProperty(DB_NAME_PROP);
		
		return String.format("jdbc:postgresql://%s:%s/%s", host, port, dbName);
	}

}
