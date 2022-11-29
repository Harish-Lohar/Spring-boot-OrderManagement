package com.ordermanagement.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String login (@PathVariable("email") String email,@PathVariable("password") String password){
		
		return userService.validateUser(email,password);
	}
	
	@PutMapping("/updateuser/{userId}")
	public String updateUser(@PathVariable("userId") Long userId,@RequestBody UserDto userDto) 
	{
		return userService.updateUser(userId,userDto);
	}
	
	@GetMapping("/Users")
	public List<Users> AllUsers(){
		return userService.allUsers();
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public String deleteuser(@PathVariable("id") Long id ) {
		return userService.userDelete(id);
	}
	
	// Email
	@GetMapping("/getemails")
	public Collection<String> getAllEmails()
	{
//		List<String> emaiList=userService.getAllUsers().stream().map(x-> x.getEmail()).collect(Collectors.toList());
		
		return userService.getAllUsers();
	}
	
}
