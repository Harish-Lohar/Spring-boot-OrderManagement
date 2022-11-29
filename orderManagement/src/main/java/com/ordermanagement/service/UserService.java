package com.ordermanagement.service;

import java.util.Collection;
import java.util.List;

import com.ordermanagement.dto.UserDto;
import com.ordermanagement.model.Users;

public interface UserService {

	String saveUser(UserDto userDto);

	String validateUser(String email, String password);

	List<Users> allUsers();

	String userDelete(Long id);

	String updateUser(Long userId, UserDto userDto);

	Collection<String> getAllUsers();

	

	




}
