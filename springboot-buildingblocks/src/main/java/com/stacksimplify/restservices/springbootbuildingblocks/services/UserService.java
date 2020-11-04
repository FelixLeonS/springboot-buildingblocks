package com.stacksimplify.restservices.springbootbuildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserExistsException;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.repositories.UserRepository;

//Service
@Service
public class UserService {
	
	//Auto wire User repository
	@Autowired
	private UserRepository userRepository;
	
	//Get all users method
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}
	
	
	//Get user by Id method
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		
		 Optional<User> user = userRepository.findById(id);
		 if(!user.isPresent()) {
			 throw new UserNotFoundException("User not found by given ID in repository");
		 }
		 return user;
	}
	
	//Get user by userName method
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	//Create user Method
	public User createUser(User user) throws UserExistsException {
		//check if user exists using name
		User existingUser = userRepository.findByUsername(user.getUsername());
		//if not exists thrown an exception
		if(existingUser!=null) {
			
			throw new UserExistsException("User already exists in the repository");
		}
		
		return userRepository.save(user);

	}
	
	//Update user by Id Method
	public User updateUser(User user, Long id) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
		 if(!optionalUser.isPresent()) {
			 throw new UserNotFoundException("User not found by given ID in repository, please provide a existing user ID");
		 }
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete user by Id
	public void deleteUserById(long id)  {
		
		Optional<User> optionalUser = userRepository.findById(id);
		 if(!optionalUser.isPresent()) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found by given ID in repository, please provide a existing user ID");
		 }
		userRepository.deleteById(id);
		
	}
	
	
}
