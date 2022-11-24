package com.ordermanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.dao.UserRepository;
import com.ordermanagement.dto.UserDto;
import com.ordermanagement.model.Users;
import com.ordermanagement.service.UserService;

import io.swagger.models.properties.EmailProperty;

@Service
public class UserSerciveImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public String saveUser(UserDto userDto) {
		Users users = new Users();
		Optional<Users> id =userRepository.findByUserId(userDto.getUserId());
		if (!id.isPresent()) {
			
		}else {
			return "Email Already Exist...";
		}
		users.setPersoneName(userDto.getPersoneName());
		users.setAddress(userDto.getAddress());
		
		Optional<Users> email = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
		if (!email.isPresent()) {
			users.setEmail(userDto.getEmail());
		}else {
			return "Email Already Exist...";			
		}

		Optional<Users> contact = userRepository.findByContact(userDto.getContact());
		if (!contact.isPresent()) {
			users.setContact(userDto.getContact());
			users.setEmail(userDto.getEmail());
			users.setPassword(userDto.getPassword());
			userRepository.save(users);
			return "User Registration Successfully";

		} else {
			return "Contact Already Exist...";
		}
	}

	@Override
	public String validateUser(String email, String password) {
		Users users =userRepository.findByEmail(email);
		if (password.equals(users.getPassword())) {
			return "Login Successful...";
			
		} else {

			return "Login Failed...";
		}
		
	}

	@Override
	public String updateUser(Long userId, UserDto userDto) {
		Optional<Users> name = userRepository.findByUserId(userId);
		if (name.isPresent()) {
			Users users = userRepository.getByContact(userDto.getContact());
			users.setAddress(users.getAddress());
			users.setPersoneName(users.getPersoneName());
			users.setPassword(users.getPassword());
			userRepository.save(users);
			return "User Data update Successfully...";
		} else {
			return "User Not Exist...";
		}
	}

	@Override
	public List<Users> allUsers() {
		
		return userRepository.findAll();
	}
}
