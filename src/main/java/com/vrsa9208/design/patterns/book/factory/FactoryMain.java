package com.vrsa9208.design.patterns.book.factory;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vrsa9208.design.patterns.book.factory.dao.ProductDao;
import com.vrsa9208.design.patterns.book.factory.model.Product;
import com.vrsa9208.design.patterns.book.factory.values.DBType;

public class FactoryMain {
	private static final Logger LOGGER = LogManager.getLogger(FactoryMain.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LOGGER.debug("Selecciona una base de datos para conectar:");
		LOGGER.debug("\t1)MySQL");
		LOGGER.debug("\t2)PostgreSQL");
		LOGGER.debug("\t3)SQL Server");
		
		int selectedOption = scanner.nextInt();
		DBType selectedDbType;
		switch(selectedOption) {
		case 1:
			selectedDbType = DBType.MYSQL;
			break;
		case 2:
			selectedDbType = DBType.POSTGRESQL;
			break;
		case 3:
			selectedDbType = DBType.SQLSERVER;
			break;
		default:
			scanner.close();
			throw new IllegalArgumentException();
		}
		
		scanner.close();
		showProducts(selectedDbType);
	}
	
	private static void showProducts(DBType dbType) {
		LOGGER.debug(String.format("Showing products in %s database ==>", dbType)); 
		ProductDao productDao = new ProductDao(dbType);
		
		List<Product> products = productDao.findAllProducts();
		for(Product product : products){ 
			LOGGER.debug(product); 
		}
	}
}
