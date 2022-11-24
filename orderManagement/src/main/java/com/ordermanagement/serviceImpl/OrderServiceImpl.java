package com.ordermanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.dao.OrderRepository;
import com.ordermanagement.dto.OrderDto;
import com.ordermanagement.model.Order;
import com.ordermanagement.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	public static int ordernum = 0;

	@Override
	public String saveOrder(OrderDto orderDto) {
		Order order = new Order();
		order.setUserId(orderDto.getUserId());
		order.setTokennumber(++ordernum);
		order.setOrderName(orderDto.getOrderName());
		order.setQuantity(orderDto.getQuantity());
		order.setPrice(orderDto.getPrice());
		orderRepository.save(order);
		return "Your " + orderDto.getOrderName() + " order Placed Successfully,Your Token ID is " + ordernum;
	}

	@Override
	public List<Order> orders() {

		return orderRepository.findAll();
	}

	@Override
	public String updateOrder(int tokenNumber, OrderDto orderDto) {
		Optional<Order> token = orderRepository.findByTokenNumber(tokenNumber);
		if (token.isPresent()) {
			Order order = orderRepository.getByTokenNumber(tokenNumber);
			order.setOrderName(orderDto.getOrderName());
			order.setPrice(orderDto.getPrice());
			order.setQuantity(orderDto.getQuantity());
			orderRepository.save(order);
			return "Order updated Successfully";
		} else {
			return "Order not Exist...";
		}
	}

	@Override
	public String deleteOrder(int tokenNumber) {
		System.out.println(tokenNumber);
		Optional<Order> token = orderRepository.findByTokenNumber(tokenNumber);
		System.out.println(token.isPresent());
		if (token.isPresent()) {
			orderRepository.deleteByTokenNumber(tokenNumber);;
			return "Order Cancle Successfully...";
		} else {
			return "Order not Exist...";
		}
	}

}
