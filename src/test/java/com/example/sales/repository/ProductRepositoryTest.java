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
import org.springframework.dao.DataIntegrityViolationException;
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
	public void testSaveWithOnlyRequiredFields() {
		assertEquals(product.getName(), ProductBuilder.DEFAULT_PRODUCT_NAME);
		assertEquals(product.getPrice(), ProductBuilder.DEFAULT_PRODUCT_PRICE);
		assertNotNull(product.getId());
    }
	@Test
	public void testSaveWithAllFields() {
		
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

	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveWithNameExists() {
		Product productSave = ProductBuilder.create()
		 
				.build();
		
		this.productRepository.save(productSave);
		
    }
	
	@Test
	public void testFindByNameExists() {
		Optional<Product> productOpt = this.productRepository.findByName(ProductBuilder.DEFAULT_PRODUCT_NAME);
		
		assertTrue(productOpt.isPresent());
		assertEquals(product.getName(), ProductBuilder.DEFAULT_PRODUCT_NAME);
		assertEquals(product.getPrice(), ProductBuilder.DEFAULT_PRODUCT_PRICE);
		assertNotNull(product.getId());
    }
	
	@Test
	public void testFindByNameNotExists() {
		Optional<Product> productOpt = this.productRepository.findByName("Product 2");
		assertTrue(!productOpt.isPresent());
    }
	
	
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveWithNullInNotNullFields() {
		Product productSave = ProductBuilder.create()
		  	.name(null)
		  	.price(null)
		.build();
		
		this.productRepository.save(productSave);
		
    }
	
	
	@Test
	public void testFindByIdExists() {
		Optional<Product> productFindOpt = this.productRepository.findById(product.getId());
		
		Product productFind = productFindOpt.get();
		
		assertTrue(productFindOpt.isPresent());
		assertEquals(productFind.getName(), ProductBuilder.DEFAULT_PRODUCT_NAME);
		assertEquals(productFind.getPrice(), ProductBuilder.DEFAULT_PRODUCT_PRICE);
		assertNotNull(productFind.getId());
    }
	
	
	@After
	public final void tearDown() {
		this.productRepository.deleteAll();
	}
	

}
