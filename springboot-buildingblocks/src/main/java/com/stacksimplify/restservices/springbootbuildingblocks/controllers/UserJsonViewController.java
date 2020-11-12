package com.stacksimplify.restservices.springbootbuildingblocks.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.entities.Views;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

@RestController
@Validated
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {

	@Autowired
	private UserService userService;
	//Get userbyId external
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional <User> getUserByIdExternal(@PathVariable("id") @Min(1) Long id ){
		try {
		return userService.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	//Get userbyId internal
	@GetMapping("/internal/{id}")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(Views.Internal.class)
	public Optional <User> getUserByIdInternal(@PathVariable("id") @Min(1) Long id ){
		try {
		return userService.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
}
