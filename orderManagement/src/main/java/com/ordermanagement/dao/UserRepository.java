package com.ordermanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordermanagement.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	Users findByEmail(String email);

	Users getByContact(Long contact);

	Optional<Users> findByContact(Long contact);

	Optional<Users> findByUserId(Long userId);

}
