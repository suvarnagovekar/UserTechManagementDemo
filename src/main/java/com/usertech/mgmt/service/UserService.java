package com.usertech.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usertech.mgmt.Repository.UserRepository;
import com.usertech.mgmt.entity.UserDetails;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserTechService userTechService;
	
	public List<UserDetails> getAllUsers()
	{
		List<UserDetails> userList = new ArrayList<UserDetails>();
		userRepository.findAll(new PageRequest(0,5)).forEach(userList::add);
		return userList;
	}
	
	public UserDetails addUser(UserDetails user)
	{
		return userRepository.save(user);
	}
	
	public void updateUser(int id , UserDetails user)
	{
		userRepository.save(user);
	}
	
	public Optional<UserDetails> getUser(int id)
	{
		return userRepository.findById(id);
	}
	
	@Transactional
	public void deleteUser(int id)
	{
		userRepository.deleteById(id);
		userTechService.deleteUserIds(id);
	}
	
	
	public List<UserDetails> getUsersForIds(List<Integer> userIds)
	{
		List<UserDetails> userList = new ArrayList<UserDetails>();
		Iterable<Integer> list = userIds;
		userRepository.findAllById(list).forEach(userList::add);
		return userList;
	}
	
	
	
	

}
