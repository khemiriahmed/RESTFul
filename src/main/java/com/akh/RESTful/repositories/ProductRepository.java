package com.akh.RESTful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akh.RESTful.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
