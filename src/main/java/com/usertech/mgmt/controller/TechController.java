package com.usertech.mgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usertech.mgmt.entity.Technology;
import com.usertech.mgmt.exception.ResourceNotFoundException;
import com.usertech.mgmt.service.TechService;
import com.usertech.mgmt.service.UserTechService;

@RestController
@RequestMapping("/api")
public class TechController {
	
	@Autowired
	TechService techService;
	
	@PostMapping("/addtech")
	public void addTech(@RequestBody Technology technology)
	{
		techService.addTech(technology);
	}
	
	@GetMapping("/tech/{id}")
	public Technology getTech(@PathVariable Integer id) throws ResourceNotFoundException
	{
		Technology technology = techService.getTech(id).orElseThrow(()->new ResourceNotFoundException("Technology Not found with given id"));
		return technology;
	}
	
	@PutMapping("/tech/{id}")
	public void updateTech(@PathVariable Integer id , @RequestBody Technology technology) throws ResourceNotFoundException
	{
		techService.getTech(id).orElseThrow(()-> new ResourceNotFoundException("Technology Not found with given id"));
		techService.updateTech(id, technology);
	}
	
	@GetMapping("/getalltech")
	public List<Technology> getAllTechs()
	{
		return techService.getAllTech();
	}
	
	@DeleteMapping("/tech/{id}")
	public void deleteTech(@PathVariable Integer id) throws ResourceNotFoundException
	{
		techService.getTech(id).orElseThrow(()-> new ResourceNotFoundException("Technology Not found with given id"));
		techService.deleteTech(id);
	}

}
