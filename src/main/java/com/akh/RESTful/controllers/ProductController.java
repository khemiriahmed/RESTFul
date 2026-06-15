package com.akh.RESTful.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akh.RESTful.models.Product;
import com.akh.RESTful.models.ProductDto;
import com.akh.RESTful.repositories.ProductRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/products")
public class ProductController {
 @Autowired
	private ProductRepository repo;
	
	@GetMapping
	public List<Product>getProduits(){
		return repo.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product>getProduit(@PathVariable int id){
		Product product = repo.findById(id).orElse(null);
        if(product == null) {
        	return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
	}
	
	@PostMapping
	 public ResponseEntity<Object> createProduct(
	            @Valid @RequestBody ProductDto productDto,
	            BindingResult result) {
		
		        double price = 0;
		        try {
		        	price = Double.parseDouble(productDto.getPrice());
		        	
		        }
		        catch(Exception ex) {
		        	result.addError(new FieldError("productDto","price","The price should be a number"));
		        }
		
		   if (result.hasErrors()) {
	            var errorsMap = new HashMap<String, String>();
	            var errorList = result.getAllErrors();
	            for (int i = 0; i < errorList.size(); i++) {
	                var error = (FieldError) errorList.get(i);
	                errorsMap.put(error.getField(), error.getDefaultMessage());
	            }
	            return ResponseEntity.badRequest().body(errorsMap);
	        }
		   
		    Product product = new Product();
            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setDesription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setPrice(price);
            product.setCreatedAt(new Date());

            repo.save(product);
		
		return ResponseEntity.ok(product);
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Object> updateProduct(
			@PathVariable int id,
			@Valid @RequestBody ProductDto productDto,
            BindingResult result) {
		
		  double price = 0;
	        
	        try {
	        	price = Double.parseDouble(productDto.getPrice());
	        	
	        }
	        catch(Exception ex) {
	        	result.addError(new FieldError("productDto","price","The price should be a number"));
	        }
	        
	        if (result.hasErrors()) {
	            var errorsMap = new HashMap<String, String>();
	            var errorList = result.getAllErrors();
	            for (int i = 0; i < errorList.size(); i++) {
	                var error = (FieldError) errorList.get(i);
	                errorsMap.put(error.getField(), error.getDefaultMessage());
	            }
	            return ResponseEntity.badRequest().body(errorsMap);
	        }
		
		Product product = repo.findById(id).orElse(null);
        if(product == null) {
        	return ResponseEntity.notFound().build();
        }
        
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setDesription(productDto.getDescription());
        product.setPrice(price);
        product.setCreatedAt(new Date());

        repo.save(product);
	
        return ResponseEntity.ok(product);
	     
		
	}
	
	
} 
