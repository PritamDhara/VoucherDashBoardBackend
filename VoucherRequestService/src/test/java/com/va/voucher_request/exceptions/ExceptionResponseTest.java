package com.va.voucher_request.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ExceptionResponseTest {
	
	
	
	 @Test
	    void testGettersAndSetters() {
	        LocalDate timestamp = LocalDate.now();
	        String message = "Test Message";
	        String details = "Test Details";
	        String httpCodeMessage = "404 Not Found";

	        ExceptionResponse exceptionResponse = new ExceptionResponse();
	        exceptionResponse.setTimestamp(timestamp);
	        exceptionResponse.setMessage(message);
	        exceptionResponse.setDetails(details);
	        exceptionResponse.setHttpCodeMessage(httpCodeMessage);
	        

	        assertEquals(timestamp, exceptionResponse.getTimestamp());
	        assertEquals(message, exceptionResponse.getMessage());
	        assertEquals(details, exceptionResponse.getDetails());
	        assertEquals(httpCodeMessage, exceptionResponse.getHttpCodeMessage());
	    }
	 
	 
	    @Test
	    void testConstructor() {
	        LocalDate timestamp = LocalDate.now();
	        String message = "Test Message";
	        String details = "Test Details";
	        String httpCodeMessage = "404 Not Found";

	        ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp, message, details, httpCodeMessage);

	        assertEquals(timestamp, exceptionResponse.getTimestamp());
	        assertEquals(message, exceptionResponse.getMessage());
	        assertEquals(details, exceptionResponse.getDetails());
	        assertEquals(httpCodeMessage, exceptionResponse.getHttpCodeMessage());
	    }
	    

	    @Test
	    void testDefaultConstructor() {
	        ExceptionResponse exceptionResponse = new ExceptionResponse();

	        assertNull(exceptionResponse.getTimestamp());
	        assertNull(exceptionResponse.getMessage());
	        assertNull(exceptionResponse.getDetails());
	        assertNull(exceptionResponse.getHttpCodeMessage());
	    }

}

