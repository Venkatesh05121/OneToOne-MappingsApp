package com.example.demo.service;

import java.lang.System.Logger;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserDetails;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServices {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserServices.class);
	
	@Autowired
	UserRepository userRepository;
	
	//Create a User...
	public UserDetails createUser(UserDetails users) {
	UserDetails user = userRepository.save(users);
	logger.info("User Created Successfully ! : {} ", user.getUserId());
		
		return user;
		
	}
	
	//Fetch data using user id...
	public UserDetails getUserDetailsById(Long userId) {
		logger.info("Fetching User with Id : {} ", userId);
	UserDetails fetchedData	= userRepository.findById(userId).orElseThrow(() -> 
	     new UserNotFoundException("Please Checks the UserDetails, User not found...! "));
	logger.info("Successfully fetched : {} ", userId);
		
		return fetchedData;
		
	}
	
	//Update the UserDetails By Id...
	public UserDetails updateUser(Long userId, UserDetails users) {
	UserDetails existingUser = userRepository.findById(userId).orElse(null);
	
	if(existingUser != null ) {
		existingUser.setName(users.getName());
		existingUser.setEmail(users.getEmail());
		existingUser.setContact(users.getContact());
		existingUser.setAddress(users.getAddress());
		
	UserDetails updatedUser = userRepository.save(existingUser);
	logger.info("User Details was updated Successfully ! : {} ", updatedUser.getUserId());
	
	return updatedUser;
		
	}
		
		return null;
	}
	
	//Get All UserDetails...
	public List<UserDetails> retreiveAllUsers() {
	List<UserDetails> fetchAll = userRepository.findAll();
		logger.info("Successfully Retreived All UserDetails : {} ");
		
		return fetchAll;
	}

	//Deleting the User...
	public void deleteUser(Long userId) {
	    userRepository.deleteById(userId);
    logger.info("UserDetails Deleted Successfully ....");
	}
	
	
}
