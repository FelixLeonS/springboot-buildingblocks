package com.stacksimplify.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.Order;
import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
//import com.stacksimplify.restservices.springbootbuildingblocks.repositories.OrderRepository;
import com.stacksimplify.restservices.springbootbuildingblocks.repositories.UserRepository;
import com.stacksimplify.restservices.springbootbuildingblocks.services.OrderService;

@RestController
@RequestMapping(value = "/users/hateoas")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	//@Autowired
	//private OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		
		Optional <User> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent()) {
			
			throw new UserNotFoundException("User not found");
		}
		
		List<Order> allOrders = userOptional.get().getOrders();
		
		CollectionModel<Order> finalCollection = CollectionModel.of(allOrders);
		
		return finalCollection;
		
	
	}
	
	@GetMapping("/{userid}/orders/{oderid}")
	@ResponseStatus(HttpStatus.OK)
	private Optional<Order> getOrderById(@PathVariable Long userid,
										@PathVariable Long oderid) throws UserNotFoundException{
		
		Optional <User> userOptional = userRepository.findById(userid);
		
			if(!userOptional.isPresent()) {
				
				throw new UserNotFoundException("User not found");
			}
		
			try {
				return orderService.findOrderById(userid, oderid);
			}catch(OrderNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());				
			}
		
	}
	
	
	
	
	
}
