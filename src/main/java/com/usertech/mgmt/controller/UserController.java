package com.usertech.mgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usertech.mgmt.entity.UserDetails;
import com.usertech.mgmt.exception.ResourceNotFoundException;
import com.usertech.mgmt.service.UserService;
import com.usertech.mgmt.service.UserTechService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTechService userTechService;
	
	@RequestMapping("/hi")
	public String getHi()
	{
		return "Hi";
	}
	
	@GetMapping("/getusers")
	public List<UserDetails> getUsers()
	{
		return userService.getAllUsers();
	}
	
	@PostMapping("/adduser")
	public UserDetails addUser(@RequestBody UserDetails user)
	{
		System.out.println("add request");
		return userService.addUser(user);
	}
	
	@GetMapping("/user/{id}")
	public UserDetails getUser(@PathVariable Integer id) throws ResourceNotFoundException
	{
		UserDetails user= userService.getUser(id).orElseThrow(()->new ResourceNotFoundException("User Not Found with given id"));
		return user;
		
	}
	
	@PutMapping("/user/{id}")
	public void updateUser(@PathVariable Integer id, @RequestBody UserDetails user) throws ResourceNotFoundException
	{
		 userService.getUser(id).orElseThrow(()-> new ResourceNotFoundException("User not found with given id"));
		 userService.updateUser(id,user);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Integer id) throws ResourceNotFoundException
	{
		userService.getUser(id).orElseThrow(()->new ResourceNotFoundException("User not found with given id"));
		userService.deleteUser(id);
		
		
	}
	
	
}
