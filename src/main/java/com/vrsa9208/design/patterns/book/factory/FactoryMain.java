package com.vrsa9208.design.patterns.book.factory;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vrsa9208.design.patterns.book.factory.dao.ProductDao;
import com.vrsa9208.design.patterns.book.factory.model.Product;

public class FactoryMain {
	private static final Logger LOGGER = LogManager.getLogger(FactoryMain.class);

	public static void main(String[] args) {
		//Creamos los nuevos productos a registrar 
		Product productA = new Product(1, "Producto A", 100); 
		Product productB = new Product(2, "Producto B", 100); 
		//Creamos una instancia del DAO 
		ProductDao productDAO = new ProductDao(); 
		//Persistimos los productos 
		productDAO.saveProduct(productA); 
		productDAO.saveProduct(productB); 
		//Consultamos nuevamente los productos 
		List<Product> products = productDAO.findAllProducts(); 
		LOGGER.debug("Product size ==> " + products.size()); 
		for(Product product : products){ 
			LOGGER.debug(product); 
		}
	}
}
