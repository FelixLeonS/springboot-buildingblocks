package com.stacksimplify.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.Order;
import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
//import com.stacksimplify.restservices.springbootbuildingblocks.repositories.UserRepository;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	// @Autowired
	// private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {

		List<User> allUsers = userService.getAllUsers();

		for (User user : allUsers) {
			// Self Link
			Long userid = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			//Build Relationship link between Users and Orders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(userid);
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		//SelfLink for get all users
		Link selfLinkGetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalCollection = CollectionModel.of(allUsers,selfLinkGetAllUsers);
		return finalCollection;

	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalResource = EntityModel.of(user);
			return finalResource;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}
