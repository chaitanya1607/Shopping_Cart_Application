package com.mindtree.shoppingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingapp.converter.ApparalConverter;
import com.mindtree.shoppingapp.converter.BookConverter;
import com.mindtree.shoppingapp.dto.ApparalProductDto;
import com.mindtree.shoppingapp.dto.BookProductDto;
import com.mindtree.shoppingapp.entity.Product;
import com.mindtree.shoppingapp.repository.ProductRepository;
import com.mindtree.shoppingapp.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BookConverter bookConverter;

	@Autowired
	private ApparalConverter apparalConverter;

	@Override
	public Product createBookProduct(BookProductDto bookProductDto) {

		return productRepository.save(bookConverter.dtoToEntity(bookProductDto));
	}

	@Override
	public Product createApparalProduct(ApparalProductDto apparalProductDto) {

		return productRepository.save(apparalConverter.dtoToEntity(apparalProductDto));
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

}
