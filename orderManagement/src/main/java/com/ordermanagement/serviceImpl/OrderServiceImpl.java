package com.ordermanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ordermanagement.dao.OrderRepository;
import com.ordermanagement.dto.OrderDto;
import com.ordermanagement.model.Order;
import com.ordermanagement.service.OrderService;
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	public static int ordernum = 0;

	@Override
	public ResponseEntity<Object> saveOrder(OrderDto orderDto) {
		Order order = new Order();
		order.setUserId(orderDto.getUserId());
		order.setTokennumber(++ordernum);
		order.setOrderName(orderDto.getOrderName());
		order.setQuantity(orderDto.getQuantity());
		order.setPrice(orderDto.getPrice());
		orderRepository.save(order);
		return new ResponseEntity<>("Your " + orderDto.getOrderName() + " order Placed Successfully,Your Token ID is " + ordernum, HttpStatus.OK);
	}

	@Override
	public List<Order> orders() {
		return orderRepository.findAll();
	}

	@Override
	public ResponseEntity<Object> updateOrder(int tokenNumber, OrderDto orderDto) {
		Optional<Order> token = orderRepository.findByTokenNumber(tokenNumber);
		if (token.isPresent()) {
			Order order = orderRepository.getByTokenNumber(tokenNumber);
			order.setOrderName(orderDto.getOrderName());
			order.setPrice(orderDto.getPrice());
			order.setQuantity(orderDto.getQuantity());
			orderRepository.save(order);
			return new ResponseEntity<>("Order updated Successfully...", HttpStatus.OK);		
		} else {
			return new ResponseEntity<>("Order Not Exist...", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Object> deleteOrder(int tokenNumber) {
		System.out.println(tokenNumber);
		Optional<Order> token = orderRepository.findByTokenNumber(tokenNumber);
		System.out.println(token.isPresent());
		if (token.isPresent()) {
			orderRepository.deleteByTokenNumber(tokenNumber);
			return new ResponseEntity<>("Order Cancle Successfully...", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Order Not Exist...", HttpStatus.NOT_FOUND);
		}
	}

}
