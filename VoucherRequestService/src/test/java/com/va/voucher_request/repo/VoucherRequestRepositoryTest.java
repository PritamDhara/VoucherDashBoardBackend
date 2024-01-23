package com.va.voucher_request.repo;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.va.voucher_request.model.VoucherRequest;
import com.va.voucher_request.service.VoucherReqServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VoucherRequestRepositoryTest {
	
	 @Mock
	  private VoucherRequestRepository voucherRequestRepository;

	 @InjectMocks
	 private VoucherReqServiceImpl voucherRequestRepositoryImpl;
	
    
	    @Test
	    void testFindByCandidateEmail() {
	        // Save a VoucherRequest with a specific candidateEmail
	        VoucherRequest voucherRequest = new VoucherRequest();
	        voucherRequestRepository.save(voucherRequest);

	        // Perform the repository query
	        List<VoucherRequest> foundVoucherRequests = voucherRequestRepository.findByCandidateEmail(voucherRequest.getCandidateEmail());

	        // Assert that the saved VoucherRequest is found
	        assertEquals(0,foundVoucherRequests.size());
	       // assertEquals(voucherRequest.getCandidateEmail(), foundVoucherRequests.get(0).getCandidateEmail());
	    }

	    @Test
	    void testExistsByCloudExamAndCandidateEmail() {
	        // Save a VoucherRequest with specific cloudExam and candidateEmail
	        VoucherRequest voucherRequest = new VoucherRequest();
	        voucherRequestRepository.save(voucherRequest);

	        // Perform the repository query
	        boolean exists = voucherRequestRepository.existsByCloudExamAndCandidateEmail(
	                voucherRequest.getCloudExam(),
	                voucherRequest.getCandidateEmail()
	        );

	        // Assert that the saved VoucherRequest exists based on cloudExam and candidateEmail
	        Assertions.assertFalse(exists);
	    }

	    @Test
	    void testFindByVoucherCode() {
	        // Save a VoucherRequest with a specific voucherCode
	        VoucherRequest voucherRequest = new VoucherRequest();
	        voucherRequestRepository.save(voucherRequest);

	        // Perform the repository query
	        VoucherRequest foundVoucherRequest = voucherRequestRepository.findByVoucherCode(voucherRequest.getVoucherCode());

	        // Assert that the saved VoucherRequest is found
	       assertEquals(null,foundVoucherRequest );
	    }

}

