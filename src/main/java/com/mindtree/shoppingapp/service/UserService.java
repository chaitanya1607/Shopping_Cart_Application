package com.mindtree.shoppingapp.service;

import java.util.List;

import com.mindtree.shoppingapp.dto.UserDto;
import com.mindtree.shoppingapp.entity.User;

public interface UserService {
	User createUser(UserDto userDto);
	List<User> getAllUsers();
}
