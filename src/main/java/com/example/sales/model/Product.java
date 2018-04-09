package com.example.sales.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false, unique=true)
	private String name;

	@Column(name = "price", nullable = false, scale=2,length=10)
	private BigDecimal price;

	@Column(name = "description", nullable = true, length=200)
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
