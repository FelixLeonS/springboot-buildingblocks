package com.stacksimplify.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.Order;
import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.repositories.OrderRepository;
import com.stacksimplify.restservices.springbootbuildingblocks.repositories.UserRepository;
import com.stacksimplify.restservices.springbootbuildingblocks.services.OrderService;

@RestController
@RequestMapping(value = "/users")
@ResponseStatus(HttpStatus.OK)
public class OrderController {
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		
		Optional <User> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent()) {
			
			throw new UserNotFoundException("User not found");
		}
		
		return userOptional.get().getOrders();
		
	
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
		
		
	
	@PostMapping("/{userid}/orders")
	@ResponseStatus(HttpStatus.CREATED)
	public Order createOrder(@PathVariable Long userid,@RequestBody Order order)throws UserNotFoundException  {
		
			Optional <User> userOptional = userRepository.findById(userid);
			if(!userOptional.isPresent()) {
			
			throw new UserNotFoundException("User not found");
		}
			
			User user = userOptional.get();
			order.setUser(user);
			return orderRepository.save(order);
		
	}
	
	
	
}
