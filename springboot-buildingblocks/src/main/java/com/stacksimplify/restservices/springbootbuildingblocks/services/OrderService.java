package com.stacksimplify.restservices.springbootbuildingblocks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.Order;
import com.stacksimplify.restservices.springbootbuildingblocks.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	public OrderRepository orderRepository;

	public Optional <Order> findOrderById(Long UserId, Long OrderId) throws OrderNotFoundException{

		Optional<Order> order = orderRepository.findById(OrderId);
 		if(!order.isPresent()) {
 			
 			throw new OrderNotFoundException("Order not found");
 		}
 		return order;
		
		
	}
	
}
