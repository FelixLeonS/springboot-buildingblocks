package com.stacksimplify.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.mappers.UserMapper;
import com.stacksimplify.restservices.springbootbuildingblocks.repositories.UserRepository;
import com.stacksimplify.restservices.springbootbuildingblocks.services.dtos.UserMsDto;

@RestController("UserMapStreuctController")
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRepository userRepository;

	@GetMapping
	public List<UserMsDto> getAllUsersDtos() {

		return userMapper.usersToUserDto(userRepository.findAll());
	}

	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		User user = optionalUser.get();
		return userMapper.userToUserDto(user);
	}

}
