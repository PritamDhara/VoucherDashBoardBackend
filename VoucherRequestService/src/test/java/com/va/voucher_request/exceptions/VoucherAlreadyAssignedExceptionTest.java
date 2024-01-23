package com.va.voucher_request.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class VoucherAlreadyAssignedExceptionTest {

	

    @Test
    void testConstructorWithMessage() {
        String errorMessage = "Voucher is Already Assigned";

        VoucherIsAlreadyAssignedException exception = new VoucherIsAlreadyAssignedException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testDefaultConstructor() {
        VoucherIsAlreadyAssignedException exception = new VoucherIsAlreadyAssignedException();

        assertNotNull(exception);
        assertEquals(null, exception.getMessage()); // Message is not set in the default constructor
    }
}

