package com.example.sales.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sales.AppException;
import com.example.sales.model.Product;
import com.example.sales.repository.ProductRepository;
import com.example.sales.testbuilder.ProductBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	private Product product;

	@Before
	public void setUp() {
		product = ProductBuilder.create().build();
	}

	@Test
	public void testSuccess() {
		BDDMockito.given(productRepository.findByName(Mockito.anyString())).willReturn(Optional.empty());

		BDDMockito.given(productRepository.save(Mockito.any(Product.class))).willReturn(product);

		assertNotNull(productService.save(product));

	}

	@Test(expected = AppException.class)
	public void testProductExists() {

		try {

			BDDMockito.given(productRepository.findByName(Mockito.anyString()))
			.willReturn(Optional.of(product));

			productService.save(product);
		} catch (AppException ex) {
			assertEquals(ex.getMessage(), "Product exists!");
			throw ex;
		}
	}

	@Test
	public void testFindByNameSuccess() {

		Product product = ProductBuilder.create().build();

		BDDMockito.given(productRepository.findByName(Mockito.anyString())).willReturn(Optional.of(product));

		Optional<Product> productFind = productService.findByName("Teste");

		assertTrue(productFind.isPresent());

	}

}
