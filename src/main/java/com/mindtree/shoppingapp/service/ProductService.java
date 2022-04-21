package com.mindtree.shoppingapp.service;

import java.util.List;

import com.mindtree.shoppingapp.dto.ApparalProductDto;
import com.mindtree.shoppingapp.dto.BookProductDto;
import com.mindtree.shoppingapp.entity.Product;

public interface ProductService {
	Product createBookProduct(BookProductDto bookProductDto);

	Product createApparalProduct(ApparalProductDto apparalProductDto);
	
	List<Product> getAllProducts();
}
