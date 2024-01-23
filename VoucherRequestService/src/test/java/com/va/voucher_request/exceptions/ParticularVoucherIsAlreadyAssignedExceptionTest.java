package com.va.voucher_request.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ParticularVoucherIsAlreadyAssignedExceptionTest {
	
    @Test
    void testConstructorWithMessage() {
        String errorMessage = "Particular Voucher Is Already Assigned";

        ParticularVoucherIsAlreadyAssignedException exception = new ParticularVoucherIsAlreadyAssignedException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testDefaultConstructor() {
        ParticularVoucherIsAlreadyAssignedException exception = new ParticularVoucherIsAlreadyAssignedException();

        assertNotNull(exception);
        assertEquals(null, exception.getMessage()); // Message is not set in the default constructor
    }

}

