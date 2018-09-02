package com.vrsa9208.design.patterns.book.factory.dao.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vrsa9208.design.patterns.book.factory.dao.adapter.IDBAdapter;
import com.vrsa9208.design.patterns.book.factory.dao.adapter.MySQLDBAdapter;
import com.vrsa9208.design.patterns.book.factory.dao.adapter.PostgreSQLDBAdapter;
import com.vrsa9208.design.patterns.book.factory.dao.adapter.SQLServerDBAdapter;
import com.vrsa9208.design.patterns.book.factory.util.PropertiesUtil;
import com.vrsa9208.design.patterns.book.factory.values.DBType;

public class DBFactory implements IDBFactory {

	private static final Logger LOGGER = LogManager.getLogger(DBFactory.class);

	private static final String DB_FACTORY_PROPERTY_URL = "factory/DBFactory.properties";
	private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";

	@Override
	public IDBAdapter getDbAdapter(DBType dbType) {
		switch (dbType) {
		case MYSQL:
			return new MySQLDBAdapter();
		case POSTGRESQL:
			return new PostgreSQLDBAdapter();
		case SQLSERVER:
			return new SQLServerDBAdapter();
		default:
			throw new IllegalArgumentException("Not supported DB");
		}
	}

	@Override
	public IDBAdapter getDefaultDbAdapter() {
		try {
			Properties prop = PropertiesUtil.loadProperties(DB_FACTORY_PROPERTY_URL);
			String defaultDBClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);

			LOGGER.debug("DefaultDBClass ==> " + defaultDBClass);
			return (IDBAdapter) Class.forName(defaultDBClass).newInstance();
		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}

}
