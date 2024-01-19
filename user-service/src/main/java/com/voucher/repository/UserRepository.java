package com.voucher.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.voucher.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{
	
	Optional<User> findByUserEmail(String userEmail);
	
	void deleteByUserEmail(String userEmail);
	
//	boolean existByUserEmail(String userEmail);

}
