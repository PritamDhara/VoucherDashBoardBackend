package com.voucher.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;


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
	@GetMapping("/getProfileImageURL/{userEmail}")
    public ResponseEntity<byte[]> getProfileImageURL(@PathVariable String userEmail) {
        try {
            Optional<User> user = service.getUserByEmail(userEmail);
            if (user.isPresent()) {
                String imagePathString = user.get().getImagePath();
                Path imagePath = Paths.get(imagePathString);

                // Check if the file exists
                if (Files.exists(imagePath)) {
                    byte[] imageBytes = Files.readAllBytes(imagePath);

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image type
                    return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            // Log the exception
            e.printStackTrace(); // Replace with your logging mechanism
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
