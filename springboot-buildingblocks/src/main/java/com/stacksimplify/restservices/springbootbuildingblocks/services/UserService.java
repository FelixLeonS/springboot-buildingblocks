package com.stacksimplify.restservices.springbootbuildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
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
	public Optional<User> getUserById(Long id) {
		
		 Optional<User> user = userRepository.findById(id);
		 
		 return user;
	}
	
	//Get user by userName method
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	//Create user Method
	public User createUser(User user) {
		
		return userRepository.save(user);
		
		
	}
	
	//Update user by Id Method
	public User updateUser(User user, Long id) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete user by Id
	public void deleteUserById(long id) {
		
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
		
	}
	
	
}
