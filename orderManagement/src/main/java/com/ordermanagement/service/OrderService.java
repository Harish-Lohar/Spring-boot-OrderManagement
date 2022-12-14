package com.ordermanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ordermanagement.dto.OrderDto;
import com.ordermanagement.model.Order;

public interface OrderService {

	ResponseEntity<Object> saveOrder(OrderDto orderDto);

	List<Order> orders();

	ResponseEntity<Object> updateOrder(int tokenNumber,OrderDto orderDto);

	ResponseEntity<Object> deleteOrder(int tokenNumber);

}
