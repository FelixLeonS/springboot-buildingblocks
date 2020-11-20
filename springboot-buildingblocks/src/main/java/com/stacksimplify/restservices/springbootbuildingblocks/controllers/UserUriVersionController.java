package com.stacksimplify.restservices.springbootbuildingblocks.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;
import com.stacksimplify.restservices.springbootbuildingblocks.services.dtos.UserDtoV1;
import com.stacksimplify.restservices.springbootbuildingblocks.services.dtos.UserDtoV2;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersionController {

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	// Uri based Versioning- Version 1
	@GetMapping({"/V1.0/{id}","/V1.1/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id ) throws UserNotFoundException{
		
		Optional<User> optionalUser = userService.getUserById(id);
		
		if(!optionalUser.isPresent()) {
			
			throw new UserNotFoundException("User not found in repository");
		}
		
		User user = optionalUser.get();
		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
		return userDtoV1;
		
	}
	// Uri based Versioning- Version 2
	@GetMapping("/V2.0/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id ) throws UserNotFoundException{
		
		Optional<User> optionalUser = userService.getUserById(id);
		
		if(!optionalUser.isPresent()) {
			
			throw new UserNotFoundException("User not found in repository");
		}
		
		User user = optionalUser.get();
		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
		return userDtoV2;
		
	}
	
	

}
