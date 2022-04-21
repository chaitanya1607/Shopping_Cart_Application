package com.mindtree.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.shoppingapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
