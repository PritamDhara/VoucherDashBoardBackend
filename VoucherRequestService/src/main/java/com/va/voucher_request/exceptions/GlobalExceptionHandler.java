package com.va.voucher_request.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ScoreNotValidException.class)
	public ResponseEntity<String> handleScoreNotValidException(ScoreNotValidException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(VoucherNotFoundException.class)
	public ResponseEntity<String> handleVoucherNotFoundException(VoucherNotFoundException ex) {
		return new ResponseEntity<>("Voucher Not Found With This Id", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ParticularVoucherIsAlreadyAssignedException.class)
	public ResponseEntity<String> handleParticularVoucherIsAlreadyAssignedException(ParticularVoucherIsAlreadyAssignedException ex) {
		return new ResponseEntity<>("Particular Voucher Is Already Assigned to Other,Can't Use it", HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(VoucherIsAlreadyAssignedException.class)
	public ResponseEntity<String> handleVoucherIsAlreadyAssignedException(VoucherIsAlreadyAssignedException ex) {
		return new ResponseEntity<>("Voucher is Already Assigned", HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(NoVoucherPresentException.class)
	public ResponseEntity<String> handleNoVoucherPresentException(NoVoucherPresentException ex) {
		return new ResponseEntity<>("No Voucher Present", HttpStatus.NOT_FOUND);
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
