package com.example.demo.service;



import com.example.demo.entity.User;

import jakarta.validation.Valid;


public interface UserService {
	
	public Iterable<User> getAllUsers();

	public User createUser( User user) throws Exception;
	
	
}
