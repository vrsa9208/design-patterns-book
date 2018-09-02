package com.vrsa9208.design.patterns.book.factory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vrsa9208.design.patterns.book.factory.dao.adapter.IDBAdapter;
import com.vrsa9208.design.patterns.book.factory.dao.factory.DBFactory;
import com.vrsa9208.design.patterns.book.factory.dao.factory.IDBFactory;
import com.vrsa9208.design.patterns.book.factory.model.Product;
import com.vrsa9208.design.patterns.book.factory.values.DBType;

public class ProductDao {

	private static final Logger LOGGER = LogManager.getLogger(ProductDao.class);

	private IDBAdapter dbAdapter;

	public ProductDao() {
		IDBFactory dbFactory = new DBFactory();
		dbAdapter = dbFactory.getDefaultDbAdapter();
	}
	
	public ProductDao(DBType dbType) {
		IDBFactory dbFactory = new DBFactory();
		dbAdapter = dbFactory.getDbAdapter(dbType);
	}

	public List<Product> findAllProducts() {
		List<Product> productList = new ArrayList<>();

		try (Connection connection = dbAdapter.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT idProductos,productName" + ",productPrice FROM Productos");
				ResultSet results = statement.executeQuery()) {
			
			while (results.next()) {
				productList.add(new Product(results.getInt(1), results.getString(2), results.getDouble(3)));
			}
			return productList;
		} catch (Exception ex) {
			LOGGER.error(ex);
			return Collections.emptyList();
		}
	}

	public boolean saveProduct(Product product) {
		try (Connection connection = dbAdapter.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO Productos(idProductos," + "productName, productPrice) VALUES (?,?,?)")) {
			statement.setLong(1, product.getId());
			statement.setString(2, product.getName());
			statement.setDouble(3, product.getPrice());
			statement.executeUpdate();
			return true;
		} catch (Exception ex) {
			LOGGER.error(ex);
			return false;
		}
	}
}
