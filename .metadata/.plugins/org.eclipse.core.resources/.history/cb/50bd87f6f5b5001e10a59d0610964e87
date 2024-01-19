package com.voucher.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.entity.User;
import com.voucher.exceptions.UserAlreadyExistException;
import com.voucher.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws UserAlreadyExistException
	{
		Optional<User> us = service.register(user);
		return new ResponseEntity<User>(us.get(), HttpStatus.OK);
	}
	
	



}
