package com.mindtree.shoppingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.shoppingapp.converter.UserConverter;
import com.mindtree.shoppingapp.dto.UserDto;
import com.mindtree.shoppingapp.entity.Cart;
import com.mindtree.shoppingapp.entity.User;
import com.mindtree.shoppingapp.repository.CartRepository;
import com.mindtree.shoppingapp.repository.UserRepository;
import com.mindtree.shoppingapp.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserConverter userConverter;

	@Override
	public User createUser(UserDto userDto) {
		User user = userConverter.dtoToEntity(userDto);
		userRepository.save(user);
		Cart cart = new Cart();
		cart.setUser(user);
		cartRepository.save(cart);
		user.setCart(cart);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

}
