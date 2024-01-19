package com.va.voucher_request.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.va.voucher_request.model.VoucherRequest;

@Repository
public interface VoucherRequestRepository extends MongoRepository<VoucherRequest, String> {
	
	List<VoucherRequest> findByCandidateEmail(String candidateEmail);

	VoucherRequest findByVoucherCode(String voucherCode);

	boolean existsByCloudExamAndCandidateEmail(String cloudExam, String candidateEmail);
}
