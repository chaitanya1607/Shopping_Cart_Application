package com.mindtree.shoppingapp.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingapp.dto.UserDto;
import com.mindtree.shoppingapp.entity.User;

@Component
public class UserConverter {
	public User dtoToEntity(UserDto userDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(userDto, User.class);
	}
}
