package com.voucher.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.voucher.entity.User;
import com.voucher.exceptions.NotAnImageFileException;
import com.voucher.exceptions.UserAlreadyExistException;
import com.voucher.exceptions.UserIsNotPresentWithEmailException;

public interface UserService {
	
	Optional<User> register(User user) throws UserAlreadyExistException;
	
	//abstract method of image Upload in Service Interface
	String uploadProfileImage(MultipartFile file,String userEmail,String path) throws IOException, UserIsNotPresentWithEmailException, NotAnImageFileException;
	Optional<User> getUserByEmail(String userEmail);
}
