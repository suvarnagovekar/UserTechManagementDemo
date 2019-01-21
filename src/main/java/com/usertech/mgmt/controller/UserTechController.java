package com.usertech.mgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usertech.mgmt.entity.UserDetails;
import com.usertech.mgmt.entity.UserTech;
import com.usertech.mgmt.exception.ResourceNotFoundException;
import com.usertech.mgmt.service.TechService;
import com.usertech.mgmt.service.UserService;
import com.usertech.mgmt.service.UserTechService;

@RestController
@RequestMapping("/api")
public class UserTechController {

	@Autowired
	UserTechService userTechService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TechService techService;
	
	@PostMapping("/usertech")
	public void addUserTech(@RequestBody UserTech userTech) throws ResourceNotFoundException
	{
		if(!userService.getUser(userTech.getUserId()).isPresent())
		{
			throw new ResourceNotFoundException("User not found with given id");
		}
		if(!techService.getTech(userTech.getTechId()).isPresent())
		{
			throw new ResourceNotFoundException("Technology not found with given id");
			
		}
		userTechService.addUserTech(userTech);
			
		
	}
	
	@PutMapping("/usertech/{id}")
	public void updateUserTech(@PathVariable Integer id, @RequestBody UserTech userTech) throws ResourceNotFoundException
	{
		if(!userService.getUser(userTech.getUserId()).isPresent())
		{
			throw new ResourceNotFoundException("User not found with given id");
		}
		if(!techService.getTech(userTech.getTechId()).isPresent())
		{
			throw new ResourceNotFoundException("Technology not found with given id");
			
		}
		userTechService.updateUserTech(id, userTech);
	}
	
	@GetMapping("/fetchuserbytech/{id}")
	public List<UserDetails> fetchUserByTech(@PathVariable(value="id") Integer techId) throws ResourceNotFoundException
	{
		techService.getTech(techId).orElseThrow(() -> new ResourceNotFoundException("Technology not found with given id"));
		List<Integer> userIds= userTechService.fetchUserByTechId(techId);
		return userService.getUsersForIds(userIds);
		
	}
	
	@GetMapping("/getusertech")
	public List<UserTech> getAllUserTech()
	{
		return userTechService.getAllUserTech();
	}
}
