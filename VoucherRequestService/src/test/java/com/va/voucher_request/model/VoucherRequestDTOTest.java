package com.va.voucher_request.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class VoucherRequestDTOTest {

	   @Test
	    void testValidVoucherRequestDto() {
	        VoucherRequestDto voucherRequestDto = new VoucherRequestDto("1", "John Doe", "john@example.com", "AWS", "AWS Certified Developer", 80, "scoreImage", LocalDate.now());

	        assertEquals("1", voucherRequestDto.getId());
	        assertEquals("John Doe", voucherRequestDto.getCandidateName());
	        assertEquals("john@example.com", voucherRequestDto.getCandidateEmail());
	        assertEquals("AWS", voucherRequestDto.getCloudPlatform());
	        assertEquals("AWS Certified Developer", voucherRequestDto.getCloudExam());
	        assertEquals(80, voucherRequestDto.getDoSelectScore());
	        assertEquals("scoreImage", voucherRequestDto.getDoSelectScoreImage());
	    }
	   
	   
	   
}

