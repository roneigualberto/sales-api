package com.example.sales.model;

import java.math.BigDecimal;

public class Product {

	private Long id;
	private String name;
	private BigDecimal price;
	private String description;

	public Product(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Product() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}