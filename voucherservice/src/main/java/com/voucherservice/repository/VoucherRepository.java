package com.voucherservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.voucherservice.entity.Voucher;

public interface VoucherRepository extends MongoRepository<Voucher, String>{
	
	List<Voucher> findByExamName(String examName);
	 List<Voucher> findByCloudPlatform(String cloudPlatform);


}
