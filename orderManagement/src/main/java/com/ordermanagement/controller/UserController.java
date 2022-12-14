package com.ordermanagement.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.dto.UserDto;
import com.ordermanagement.model.Users;
import com.ordermanagement.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public String saveUser(@RequestBody UserDto userDto)
	{
		userService.saveUser(userDto);
		return "User Saved Successfully..";
	}
	
	@GetMapping("/login/{email},{password}")
	public ResponseEntity<Object> login (@PathVariable("email") String email,@PathVariable("password") String password){
		
		return userService.validateUser(email,password);
	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable("userId") Long userId,@RequestBody UserDto userDto) 
	{
		return userService.updateUser(userId,userDto);
	}
	
	@GetMapping("/Users")
	public List<Users> AllUsers(){
		return userService.allUsers();
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<Object> deleteuser(@PathVariable("id") Long id ) {
		return userService.deleteUser(id);
	}
	
	// Email
	@GetMapping("/getemails")
	public Collection<String> getAllEmails()
	{		
		return userService.getAllEmails();
	}
	
}
