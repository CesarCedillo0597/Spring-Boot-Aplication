package com.example.demo.service;



import com.example.demo.entity.User;

import jakarta.validation.Valid;


public interface UserService {
	
	public Iterable<User> getAllUsers();
	
	public User createUser( User user) throws Exception;
	
	public User getUserById(Long id)throws Exception;
	
	public User updateUser(User user)throws Exception;
	
	public void deleteUser(Long id) throws Exception;
}
