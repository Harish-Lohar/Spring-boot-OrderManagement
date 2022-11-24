package com.ordermanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordermanagement.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	Optional<Order> findByTokenNumber(int tokenNumber);

	Order getByTokenNumber(int tokenNumber);

	void deleteByTokenNumber(int tokenNumber);

	
}
