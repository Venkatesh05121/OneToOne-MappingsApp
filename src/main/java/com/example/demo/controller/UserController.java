package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.UserDetails;
import com.example.demo.service.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserServices userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDetails> createUser(@RequestBody UserDetails users){
	UserDetails createdUser = userService.createUser(users);
		
		return new ResponseEntity<UserDetails>(createdUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/retreiveUser/{id}")
	public ResponseEntity<UserDetails> retreiveUserDetailsById(@PathVariable("id") Long userId){
	UserDetails retreivedData = userService.getUserDetailsById(userId);
		
		return  new ResponseEntity<UserDetails>(retreivedData, HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDetails> updateUser(@PathVariable("id") Long userId, @RequestBody UserDetails users){
	UserDetails updatedUser = userService.updateUser(userId, users);
		
		return new ResponseEntity<UserDetails>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping("/retreiveAll")
	public List<UserDetails> retreiveAll(){
	  List<UserDetails> retreivesAll = userService.retreiveAllUsers();
	
	return retreivesAll;		
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") Long userId){
		
	 userService.deleteUser(userId);
	
	}

}
