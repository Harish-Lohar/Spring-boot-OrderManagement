package com.ordermanagement.service;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ordermanagement.dto.UserDto;
import com.ordermanagement.model.Users;

public interface UserService {

	ResponseEntity<Object> saveUser(UserDto userDto);

	ResponseEntity<Object> validateUser(String email, String password);

	List<Users> allUsers();

	ResponseEntity<Object> updateUser(Long userId, UserDto userDto);

	Collection<String> getAllEmails();

	ResponseEntity<Object> deleteUser(Long id);

	

	




}
