package com.voucher.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.voucher.entity.User;
import com.voucher.exceptions.NotAnImageFileException;
import com.voucher.exceptions.UserAlreadyExistException;
import com.voucher.exceptions.UserIsNotPresentWithEmailException;
import com.voucher.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService service;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws UserAlreadyExistException
	{
		Optional<User> us = service.register(user);
		return new ResponseEntity<User>(us.get(), HttpStatus.OK);
	}

	//mapping for uploading profile image
	@PostMapping("/uploadProfileImage/{userEmail}")
	public ResponseEntity<String> uploadProfileImage(@RequestParam("image") MultipartFile file,@PathVariable String userEmail) throws IOException, UserIsNotPresentWithEmailException, NotAnImageFileException
	{
		String res =  service.uploadProfileImage(file, userEmail, path);
		return new ResponseEntity<String>(res,HttpStatus.OK);
		
	}
	

}
