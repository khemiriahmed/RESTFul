package com.akh.RESTful.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akh.RESTful.models.Product;
import com.akh.RESTful.repositories.ProductRepository;


@RestController
@RequestMapping("api/products")
public class ProductController {
 @Autowired
	private ProductRepository repo;
	
	@GetMapping
	public List<Product>getProduits(){
		return repo.findAll();
	}
} 
