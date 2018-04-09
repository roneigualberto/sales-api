package com.example.sales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sales.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

	
	Optional<Product> findByName(String name);
	
	
}
