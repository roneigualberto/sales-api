package com.example.sales.service;

import java.util.Optional;

import com.example.sales.model.Product;

public interface ProductService {

	/***
	 * Persist a product in database
	 * 
	 * @param product
	 * @return
	 */
	Product save(Product product);
	
	
	/***
	 * Search and return a product given a name
	 * @param name
	 * @return
	 */
	Optional<Product> findByName(String name);

}
