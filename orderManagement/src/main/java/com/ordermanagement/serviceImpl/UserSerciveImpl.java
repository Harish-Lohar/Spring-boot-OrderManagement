package com.ordermanagement.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		int max = 10000000;
		int min = 20000000;
		Long a = (long) (Math.random() * (max - min + 1) + min);
		Users users = new Users();
		Optional<Users> id = userRepository.findByUserId(userDto.getUserId());
		if (!id.isPresent()) {
			users.setUserId(a);
		} else {
			return "UserID Already Exist...";
		}

		Optional<Users> email = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
		if (!email.isPresent()) {
			users.setEmail(userDto.getEmail());
		} else {
			return "Email Already Exist...";
		}

		Optional<Users> contact = userRepository.findByContact(userDto.getContact());
		if (!contact.isPresent()) {
			users.setPersoneName(userDto.getPersoneName());
			users.setAddress(userDto.getAddress());
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
		Users users = userRepository.findByEmail(email);
		if (password.equals(users.getPassword())) {
			return "Login Successful...";
		} else {
			return "Login Failed...";
		}
	}

	@Override
	public List<Users> allUsers() {
		return userRepository.findAll();
	}

	@Override
	public String userDelete(Long id) {
		userRepository.deleteById(id);
		return "User Deleted Successfully...";
	}

	@Override
	public String updateUser(Long userId, UserDto userDto) {
		Optional<Users> id1 = userRepository.findByUserId(userId);
		if (id1.isPresent()) {
			Users users = userRepository.getByUserId(userId);
			users.setAddress(userDto.getAddress());
			users.setPersoneName(userDto.getPersoneName());
			users.setPassword(userDto.getPassword());
			userRepository.save(users);
			return "User Data update Successfully...";
		} else {
			return "User Not Exist...";
		}
	}

	@Override
	public Collection<String> getAllUsers() {
		Collection<String> emaiList=userRepository.findAll().stream().map(x-> x.getEmail()).collect(Collectors.toList());
		
		Iterator<String> itr=emaiList.iterator();
		List<String> list=new ArrayList<String>();
		while (itr.hasNext()) {
			list.add(itr.next());
		}
		 Collections.reverse(list);
		return list;
	}

	

	
}
