package com.mindtree.shoppingapp.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingapp.dto.BookProductDto;
import com.mindtree.shoppingapp.entity.Book;

@Component
public class BookConverter {
	public Book dtoToEntity(BookProductDto bookProductDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(bookProductDto, Book.class);
	}
}
