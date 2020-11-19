package com.stacksimplify.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserExistsException;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//Controller
@Api(tags = "Controller for User Management service", value = "User Controller", description ="Controller for User Management service")
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {

	
	//Auto wire the service
	@Autowired
	private UserService userService;
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers(){
		
		 return userService.getAllUsers();
	}
	//Get user by Id
	@ApiOperation(value = "retive list of users")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable("id") @Min(1) Long id ){
		try {
		Optional<User> optionalUser = userService.getUserById(id);
		return optionalUser.get();
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	//Get user by userName
	@ApiOperation(value = "retive a single user by it's id")
	@GetMapping("byusername/{username}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserByUsername(@PathVariable("username") String username)throws UserNameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		if(user == null) {
			throw  new UserNameNotFoundException("Username " + username +" Does not exist in User repository ");
		}
		return user;
	}
	
	//Create User
	//RequestBody
	//@PostMapping
	@ApiOperation(value = "create a new user")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> createUser(@ApiParam("Info for new user to be created") @Valid @RequestBody User user,UriComponentsBuilder builder) {
		try {
		userService.createUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	//Update user by Id
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user) {
		try {
			return userService.updateUser(user, id);
		
		}catch(UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//Delete user by Id
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable("id") Long id) {
		
		userService.deleteUserById(id);
	}
}

