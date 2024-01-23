package com.va.voucher_request.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class VoucherTest {
	

    @Test
    void testParameterizedConstructor() {
        String id = "123";
        String cloudPlatform = "AWS";
        String examName = "AWS Certified Developer";
        String voucherCode = "ABC123";
        LocalDate issuedDate = LocalDate.of(2023, 1, 1);
        LocalDate expiryDate = LocalDate.of(2023, 12, 31);
        String issuedTo = "John Doe";

        Voucher voucher = new Voucher(id, cloudPlatform, examName, voucherCode, issuedDate, expiryDate, issuedTo);

        assertNotNull(voucher);
        assertEquals(id, voucher.getId());
        assertEquals(cloudPlatform, voucher.getCloudPlatform());
        assertEquals(examName, voucher.getExamName());
        assertEquals(voucherCode, voucher.getVoucherCode());
        assertEquals(issuedDate, voucher.getIssuedDate());
        assertEquals(expiryDate, voucher.getExpiryDate());
        assertEquals(issuedTo, voucher.getIssuedTo());
    }
    
    @Test
    void testDefaultConstructor() {
        Voucher voucher = new Voucher();

        assertNotNull(voucher);
        // Assert other fields as needed
    }

}

