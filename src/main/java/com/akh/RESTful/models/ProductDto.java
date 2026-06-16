package com.akh.RESTful.models;

import jakarta.validation.constraints.*;

public class ProductDto {

	@NotEmpty(message = "The name required")
	private String name;
	@NotEmpty(message = "The brand required")
	private String brand;
	@NotEmpty(message = "The category required")
	private String category;
	@Min(0)

	private String price;
	@Size(min = 10, message = "the Desciption should be at least 10 characters")
	@Size(max = 10, message = "the Desciption cannot  exceed 100 characters")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
