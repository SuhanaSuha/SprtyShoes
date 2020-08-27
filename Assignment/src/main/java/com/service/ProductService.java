package com.service;

import java.util.List;


import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDAO;
import com.model.Product;
 


@Component
public class ProductService {

	 @Autowired
	 private ProductDAO productDAO;


		@Transactional
		public Product getProductById(long id) {
			return productDAO.getProductById(id);
		}
		
		
		@Transactional
		public void updateProduct(Product product) {
			productDAO.updateProduct(product);
		}
		

		@Transactional
		public void deleteProduct(long id) {
			productDAO.deleteProduct(id);
		}

		@Transactional
		public List<Product> getAllProducts() {
			return productDAO.getAllProducts();
		}
	 
		@Transactional
		public List<Product> getAllProductsWithJoin() {
			return productDAO.getAllProducts();
		}
	 		
}
