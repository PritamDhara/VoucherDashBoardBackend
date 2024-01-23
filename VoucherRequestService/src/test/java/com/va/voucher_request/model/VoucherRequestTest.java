package com.va.voucher_request.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class VoucherRequestTest {
	
	

    @Test
    void testConstructorAndGetters() {
        VoucherRequest voucherRequest = new VoucherRequest("1", "John Doe", "john@example.com", "AWS", "AWS Certified Developer", 80, "scoreImage", "V123", LocalDate.now(), LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(2), "Pass");

        assertEquals("1", voucherRequest.getId());
        assertEquals("John Doe", voucherRequest.getCandidateName());
        assertEquals("john@example.com", voucherRequest.getCandidateEmail());
        assertEquals("AWS", voucherRequest.getCloudPlatform());
        assertEquals("AWS Certified Developer", voucherRequest.getCloudExam());
        assertEquals(80, voucherRequest.getDoSelectScore());
        assertEquals("scoreImage", voucherRequest.getDoSelectScoreImage());
        assertEquals("V123", voucherRequest.getVoucherCode());
        assertEquals(LocalDate.now(), voucherRequest.getVoucherIssueLocalDate());
        assertEquals(LocalDate.now().plusMonths(1), voucherRequest.getVoucherExpiryLocalDate());
        assertEquals(LocalDate.now().plusMonths(2), voucherRequest.getPlannedExamDate());
        assertEquals("Pass", voucherRequest.getExamResult());
    }

}

