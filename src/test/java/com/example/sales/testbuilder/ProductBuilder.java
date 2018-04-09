package com.example.sales.testbuilder;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.sales.model.Product;

public class ProductBuilder {
	
	public static final String DEFAULT_NAME = "Product 1";
	public static final BigDecimal DEFAULT_PRICE = new BigDecimal("1.50");

	private Optional<Long> id;
	private String name = DEFAULT_NAME;
	private BigDecimal price =  DEFAULT_PRICE;
	private Optional<String> description;

	private ProductBuilder() {

	}

	public static ProductBuilder create() {
		return create(null);
	}

	public static ProductBuilder create(Long id) {
		return new ProductBuilder().id(id);

	}

	public ProductBuilder id(Long id) {
		this.id = Optional.ofNullable(id);
		return this;
	}
	
	public ProductBuilder price(double price) {
		this.price = new BigDecimal(price);
		return this;
	}

	public ProductBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public ProductBuilder description(String desc) {
		this.description =  Optional.ofNullable(desc);
		return this;
	}

	public Product build() {

		Product product = new Product(this.name, this.price);

		this.id.ifPresent(id -> product.setId(id));
		this.description.ifPresent(desc -> product.setDescription(desc));
		
		
		return product;
	};
	
	public static void main(String[] args) {
		
		 Product produto1 = ProductBuilder
				.create()
				.build();
		 
		 
		 Product produto2 = ProductBuilder
					.create()
					.name("Product A")
					.price(10.0)
					.build();
		 
		 Product produto3 = ProductBuilder
					.create(3L)
					.name("Product 3")
					.price(2.45)
					.description("Product 3 description")
					.build();
		 
		 Product produto4 = ProductBuilder
					.create()
					.name("Product 4")
					.price(5.75)
					.description("Product 4 description")
					.build();
		 
				
	}

}
