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
import com.stacksimplify.restservices.springbootbuildingblocks.services.dtos.UserMmDto;

@RestController
@RequestMapping("/modelmapper/users")
public class ModelMapperController {

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id ) throws UserNotFoundException{
		
		Optional<User> optionalUser = userService.getUserById(id);
		
		if(!optionalUser.isPresent()) {
			
			throw new UserNotFoundException("User not found in repository");
		}
		
		User user = optionalUser.get();
		UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
		return userMmDto;
		
	}
	
	

}
