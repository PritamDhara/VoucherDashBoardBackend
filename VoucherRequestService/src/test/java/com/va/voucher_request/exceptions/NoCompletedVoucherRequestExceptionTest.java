package com.va.voucher_request.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class NoCompletedVoucherRequestExceptionTest {

	   @Test
	    void testConstructorWithMessage() {
	        String errorMessage = "No Completed Voucher Request Found";

	        NoCompletedVoucherRequestException exception = new NoCompletedVoucherRequestException(errorMessage);

	        assertNotNull(exception);
	        assertEquals(errorMessage, exception.getMessage());
	    }
}

