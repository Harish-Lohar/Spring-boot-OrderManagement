package com.ordermanagement.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ordermanagement.dao.UserRepository;
import com.ordermanagement.dto.UserDto;
import com.ordermanagement.model.Users;
import com.ordermanagement.service.UserService;

@Service
public class UserSerciveImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	// 				Save User
	@Override
	public ResponseEntity<Object> saveUser(UserDto userDto) {
		int max = 10000000;
		int min = 20000000;
		Long a = (long) (Math.random() * (max - min + 1) + min);
		Users users = new Users();
		Optional<Users> id = userRepository.findByUserId(userDto.getUserId());
		if (!id.isPresent()) {
			users.setUserId(a);
		} else {
			return new ResponseEntity<>("UserID Already Exist...", HttpStatus.ALREADY_REPORTED);
		}

		Optional<Users> email = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
		if (!email.isPresent()) {
			users.setEmail(userDto.getEmail());
		} else {
			return new ResponseEntity<>("Email Already Exist...", HttpStatus.ALREADY_REPORTED);
		}

		Optional<Users> contact = userRepository.findByContact(userDto.getContact());
		if (!contact.isPresent()) {
			users.setPersoneName(userDto.getPersoneName());
			users.setAddress(userDto.getAddress());
			users.setContact(userDto.getContact());
			users.setEmail(userDto.getEmail());
			
			//		 Encrypt Password
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
			users.setPassword(encoder.encode(userDto.getPassword()));
			userRepository.save(users);
			return new ResponseEntity<>("User Registration Successfully", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Contact Already Exist...", HttpStatus.ALREADY_REPORTED);
		}
	}

//						Validate User
	@Override
	public ResponseEntity<Object> validateUser(String email, String password) {
		Users user = userRepository.findByEmail(email);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		if (encoder.matches(password,user.getPassword())) {
			return new ResponseEntity<>("Login Successful...", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Login Failed...", HttpStatus.NOT_ACCEPTABLE);
		}
	}

//				    	All Register Users
	@Override
	public List<Users> allUsers() {
		return userRepository.findAll();
	}

//						Delete User by ID
	@Override
	public ResponseEntity<Object> deleteUser(Long id) {
		userRepository.deleteById(id);
		return new ResponseEntity<>("User Deleted Successfully...", HttpStatus.OK);
	}

//						Update User by ID
	@Override
	public ResponseEntity<Object> updateUser(Long userId, UserDto userDto) {
		Optional<Users> id1 = userRepository.findByUserId(userId);
		if (id1.isPresent()) {
			Users users = userRepository.getByUserId(userId);
			users.setAddress(userDto.getAddress());
			users.setPersoneName(userDto.getPersoneName());
			users.setPassword(userDto.getPassword());
			userRepository.save(users);
			return new ResponseEntity<>("User Data updated Successfully...", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User Not Exist...", HttpStatus.NOT_FOUND);
		}
	}

//					Get All Users only Emails list
	@Override
	public Collection<String> getAllEmails() {
		Collection<String> emaiList = userRepository.findAll().stream().map(x -> x.getEmail())
				.collect(Collectors.toList());
		Iterator<String> itr = emaiList.iterator();
		List<String> list = new ArrayList<String>();
		while (itr.hasNext()) {
			list.add(itr.next());
		}
		return list;
	}
}
