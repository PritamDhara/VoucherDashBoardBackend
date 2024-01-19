package com.voucherservice.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.voucherservice.exception.DataIsNotInsertedException;
import com.voucherservice.exception.GivenFileIsNotExcelFileException;
import com.voucherservice.exception.NoVoucherPresentException;
import com.voucherservice.exception.ResourceAlreadyExistException;
import com.voucherservice.exception.ResourceNotFoundException;
import com.voucherservice.exception.TheseDataIsAlreadyPresentException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DataIsNotInsertedException.class)
	public ResponseEntity<ExceptionResponse> handleDataIsNotInsertedException(DataIsNotInsertedException ex, WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "Data is ont inserted from Excel Sheet", request.getDescription(false), "Not Acceptable");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(GivenFileIsNotExcelFileException.class)
	public ResponseEntity<ExceptionResponse> handleGivenFileIsNotExcelException(GivenFileIsNotExcelFileException ex, WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "Uploaded File is not an excel file", request.getDescription(false), "Not Found");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoVoucherPresentException.class)
	public ResponseEntity<ExceptionResponse> handleNoVoucherPresentException(NoVoucherPresentException ex, WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "No Voucher is Present", request.getDescription(false), "Not Found");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TheseDataIsAlreadyPresentException.class)
	public ResponseEntity<ExceptionResponse> handleTheseDataIsAlreadyPresentException(TheseDataIsAlreadyPresentException ex, WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "These Data is already present in the database", request.getDescription(false), "Not Acceptable");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleOtherException(Exception ex, WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "Some Error Occured", request.getDescription(false), "Internal Server Error");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StringBuilder details = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(". ");
		}
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), "Validation fails",
				details.toString(), "Bad Request");
//		log.error("Validation fails:", ex);
//        log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex , WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Not Found");
//		log.error("Not Found"+ ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException ex , WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Already Exist");
//		log.error("Already Exist"+ ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
	}

}
