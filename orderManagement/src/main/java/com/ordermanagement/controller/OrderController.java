package com.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.dto.OrderDto;
import com.ordermanagement.dto.UserDto;
import com.ordermanagement.model.Order;
import com.ordermanagement.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService ;
	
	@PostMapping("/saveorder")
	public String OrderSave(@RequestBody OrderDto orderDto)
	{
		return orderService.saveOrder(orderDto);
	}
	
	@GetMapping("/orders")
	public List<Order> allOrders(){
		return orderService.orders();
	}
	
	@PutMapping("/update/{tokenNumber}")
	public String OrderUpdate(@PathVariable("tokenNumber") int tokenNumber,@RequestBody OrderDto orderDto) {
	return orderService.updateOrder(tokenNumber,orderDto);
	}

	@DeleteMapping("/delete/{tokenNumber}")
	public String OrderDelete(@PathVariable("tokenNumber") int tokenNumber)
	{
		return orderService.deleteOrder(tokenNumber);
	}

}
