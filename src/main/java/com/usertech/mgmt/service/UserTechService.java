package com.usertech.mgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usertech.mgmt.Repository.UserTechRepository;
import com.usertech.mgmt.entity.UserDetails;
import com.usertech.mgmt.entity.UserTech;

@Service
public class UserTechService {
	
	@Autowired
	UserTechRepository userTechRepository;
	
	public void addUserTech(UserTech userTech)
	{
		userTechRepository.save(userTech);
	}
	
	public void updateUserTech(int id , UserTech userTech)
	{
		userTechRepository.save(userTech);
	}

	public List<Integer> fetchUserByTechId(Integer techId) 
	{
		
		List<Integer> userIds = new ArrayList<Integer>();
		userTechRepository.findUserIdFromUserTech(techId).forEach(uId -> {userIds.add((Integer)uId);});
		return userIds;
		
		
	}
	
	public List<UserTech>  getAllUserTech()
	{
		List<UserTech> userTechList = new ArrayList<UserTech>();
		userTechRepository.findAll().forEach(userTechList::add);
		return userTechList;
	}
	
	public void deleteUserIds(int userId)
	{
		userTechRepository.deleteByUserId(userId);
	}
	
	public void deleteTechIds(int techId)
	{
		userTechRepository.deleteByTechId(techId);
	}
	
	
	
	
	
	

}
