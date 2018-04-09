package com.example.sales.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sales.AppException;
import com.example.sales.model.Product;
import com.example.sales.repository.ProductRepository;
import com.example.sales.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository repository;

	@Override
	public Product save(Product product) {
		
		Optional<Product> productOpt = this.repository.findByName(product.getName());
		
		if (productOpt.isPresent()) {
			throw new AppException("Product exists!");
		}
		
		return this.repository.save(product);
	}

	@Override
	public Optional<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
