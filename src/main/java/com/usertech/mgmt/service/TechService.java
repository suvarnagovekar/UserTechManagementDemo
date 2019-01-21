package com.usertech.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usertech.mgmt.Repository.TechRepository;
import com.usertech.mgmt.entity.Technology;

@Service
public class TechService {
	
	@Autowired
	TechRepository techRepository;
	
	@Autowired
	UserTechService userTechService;
	
	public void addTech(Technology tech)
	{
		techRepository.save(tech);
	}
	
	public Optional<Technology> getTech(int id) 
	{
		return techRepository.findById(id);
	}
	
	public void updateTech(int id , Technology tech)
	{
		tech.setTechId(id);
		techRepository.save(tech);
	}
	
	@Transactional
	public void deleteTech(int id)
	{
		techRepository.deleteById(id);
		userTechService.deleteTechIds(id);
	}
	
	public List<Technology> getAllTech()
	{
		List<Technology> techList = new ArrayList<Technology>();
		techRepository.findAll(new PageRequest(0,5)).forEach(techList::add);
		return techList; 
	}

}
