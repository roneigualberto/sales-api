package com.example.sales.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sales.model.Product;
import com.example.sales.testbuilder.ProductBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
	
	
	@Autowired
	private ProductRepository productRepository;
	private Product product;
	
	
	@Before
	public void setUp() {
		product = ProductBuilder.create().build();
		product = productRepository.save(product);
	}
	
	@Test
	public void testSaveSuccess() {
		assertEquals(product.getName(), ProductBuilder.DEFAULT_NAME);
		assertEquals(product.getPrice(), ProductBuilder.DEFAULT_PRICE);
		assertNotNull(product.getId());
    }
	
	@Test
	public void testSaveWithAllProperties() {
		
		final String PRODUCT_NAME = "Product A";
		final Double PRODUCT_PRICE = 1.20;
		final String PRODUCT_DESCRIPTION = PRODUCT_NAME + "Description";
		
		
		Product productSaved = ProductBuilder.create()
			.name(PRODUCT_NAME)
			.price(PRODUCT_PRICE)
			.description(PRODUCT_DESCRIPTION)
			.build();
		
		productRepository.save(productSaved);
		
		assertEquals(productSaved.getName(),PRODUCT_NAME);
		assertEquals(productSaved.getPrice(), new BigDecimal(PRODUCT_PRICE));
		assertEquals(productSaved.getDescription(),PRODUCT_DESCRIPTION);
		assertNotNull(productSaved.getId());
    }
	
	@Test
	public void testFindByIdSuccess() {
		Optional<Product> productFindOpt = this.productRepository.findById(product.getId());
		
		Product productFind = productFindOpt.get();
		
		assertTrue(productFindOpt.isPresent());
		assertEquals(productFind.getName(), ProductBuilder.DEFAULT_NAME);
		assertEquals(productFind.getPrice(), ProductBuilder.DEFAULT_PRICE);
		assertNotNull(productFind.getId());
    }
	
	
	@After
	public final void tearDown() {
		this.productRepository.deleteAll();
	}
	

}
