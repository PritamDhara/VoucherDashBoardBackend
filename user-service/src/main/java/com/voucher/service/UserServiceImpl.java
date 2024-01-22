package com.voucher.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.voucher.entity.User;
import com.voucher.exceptions.NotAnImageFileException;
import com.voucher.exceptions.UserAlreadyExistException;
import com.voucher.exceptions.UserIsNotPresentWithEmailException;
import com.voucher.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> register(User user) throws UserAlreadyExistException {
		
		Optional<User> u = repo.findByUserEmail(user.getUserEmail());
		
		if(u.isPresent())
		{
			throw new UserAlreadyExistException();
		}
		user.setUserId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User us = repo.insert(user);
		
		return Optional.of(us);
	}

	

	
	//service method for uploading profile image
	@Override
	public String uploadProfileImage(MultipartFile file, String userEmail, String path) throws IOException, UserIsNotPresentWithEmailException, NotAnImageFileException {
		
				//creating random name for unique name
				String random = UUID.randomUUID().toString();
				
				//giving the name of the file
				String name = random+file.getOriginalFilename();
				
				//checking if the file is image or not
				String extension = name.substring(name.lastIndexOf('.'));
				if(!extension.equalsIgnoreCase(".png") && !extension.equalsIgnoreCase(".jpeg")&& !extension.equalsIgnoreCase(".jpg"))
				{
					throw new NotAnImageFileException();
//					return "Give the proper file"; //exception thrown
				}
				// fetching the full path where to store
				String filePath = path+File.separator+name;
				
				//checking if the user Exist or not
				
				Optional<User> user = repo.findByUserEmail(userEmail);
				if(user.isEmpty())
				{
					throw new UserIsNotPresentWithEmailException();
				}
				
				//creating and checking if the Path exist or not
				File f = new File(path);
				if(!f.exists())
				{
					//if not exist then make this directory
					f.mkdir();
				}
				
				//file copy
				Files.copy(file.getInputStream(), Paths.get(filePath));
				
				//updating the path into the database 
				user.get().setImagePath(filePath);
				
				//save the user in database
				repo.save(user.get());
				
				
				
				return "Profile Image Uploaded Successfully";
//		return null;
	}
	
	 @Override
	    public Optional<User> getUserByEmail(String userEmail) {
	        return repo.findByUserEmail(userEmail);
	    }
	


}
