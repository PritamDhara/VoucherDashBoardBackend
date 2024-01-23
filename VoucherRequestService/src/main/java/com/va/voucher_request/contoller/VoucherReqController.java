package com.va.voucher_request.contoller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.va.voucher_request.client.VoucherClient;
import com.va.voucher_request.dto.Voucher;
import com.va.voucher_request.exceptions.NoCompletedVoucherRequestException;
import com.va.voucher_request.exceptions.NoVoucherPresentException;
import com.va.voucher_request.exceptions.NotFoundException;
import com.va.voucher_request.exceptions.ParticularVoucherIsAlreadyAssignedException;
import com.va.voucher_request.exceptions.ResourceAlreadyExistException;
import com.va.voucher_request.exceptions.ScoreNotValidException;
import com.va.voucher_request.exceptions.VoucherIsAlreadyAssignedException;
import com.va.voucher_request.exceptions.VoucherNotFoundException;
import com.va.voucher_request.model.VoucherRequest;
import com.va.voucher_request.model.VoucherRequestDto;
import com.va.voucher_request.service.EmailRequestImpl;
import com.va.voucher_request.service.VoucherReqServiceImpl;

@RestController
@RequestMapping("/requests")
@CrossOrigin("*")
public class VoucherReqController {
	
	@Autowired
	private VoucherReqServiceImpl vservice;
	@Autowired
	
	EmailRequestImpl impl;
 
	@Autowired
	VoucherClient voucherClient;
	
	@PostMapping("/voucher") //post request to request for the voucher
    public ResponseEntity<VoucherRequest> requestVoucher(@RequestBody VoucherRequestDto request) throws ScoreNotValidException, ResourceAlreadyExistException {
		VoucherRequest req = vservice.requestVoucher(request);
		return new ResponseEntity<VoucherRequest>(req,HttpStatus.OK);
    }

    @GetMapping("/{candidateEmail}") //get request to view all the vouchers by candidate's email
    public ResponseEntity<List<VoucherRequest>> getAllVouchersByCandidateEmail(@PathVariable String candidateEmail) throws NotFoundException {
    	List<VoucherRequest> list = vservice.getAllVouchersByCandidateEmail(candidateEmail);
    	return new ResponseEntity<List<VoucherRequest>>(list,HttpStatus.OK);
    }
    
    @PutMapping("/updateExamDate/{voucherCode}/{newExamDate}") //put request to update the exam date 
	public ResponseEntity<VoucherRequest> updateExamDate(@PathVariable String voucherCode,@PathVariable LocalDate newExamDate) throws NotFoundException {
    	VoucherRequest req = vservice.updateExamDate(voucherCode, newExamDate);
    	return new ResponseEntity<VoucherRequest>(req, HttpStatus.OK);
    }
		

    @PutMapping("/{voucherCode}/{newExamResult}") //put request to update the exam result
    public ResponseEntity<VoucherRequest> updateResultStatus( @PathVariable String voucherCode, @PathVariable String newExamResult) throws NotFoundException {
       
            VoucherRequest updatedVoucherRequest = vservice.updateExamResult(voucherCode, newExamResult);
          return new ResponseEntity<VoucherRequest>(updatedVoucherRequest,HttpStatus.OK);

    }
    
@GetMapping("/assignvoucher/{voucherId}/{emailId}/{voucherrequestId}")
	
    public ResponseEntity<VoucherRequest> assignVoucher(@PathVariable String voucherId,@PathVariable String emailId,@PathVariable String voucherrequestId) throws NotFoundException, VoucherNotFoundException, VoucherIsAlreadyAssignedException, ParticularVoucherIsAlreadyAssignedException {
    	Voucher voucher = voucherClient.getVoucherById(voucherId).getBody();
    	VoucherRequest request = vservice.assignVoucher(voucherId,emailId,voucherrequestId);
    	String msg = "Hi " +request.getCandidateName() + ",\r\n"
    	        + "\r\n"
    	        + "Please find the Pearson Exam voucher code for " + voucher.getExamName() + ": " + voucher.getVoucherCode() + " [Please schedule the exam directly without converting.]\r\n"
    	        + "\r\n"
    	        + "EXAM TO BE SCHEDULED AT THE NEAREST PEARSON VUE CENTRE ONLY. \r\n"
    	        + "\r\n"
    	        + "YOUR NAME IN AWS ACCOUNT AND ID PROOF SHOULD MATCH, ANY DISCREPANCY IN THE NAME ON ID PROOF WILL RESULT IN CANCELLATION OF EXAM AND VOUCHER.\r\n"
    	        + " " + "\r\n All the best 😊" +
    	        "\r\n Thanks and Regards,"
    	        + "\r\nTulsi Rao"
;
    	impl.sendEmail(emailId, "AWS voucher", msg);
    	return new ResponseEntity<>(request, HttpStatus.OK);
    			
    }
    
    @GetMapping("/getAllVouchers") //get request to view all the vouchers
    public ResponseEntity<List<VoucherRequest>> getAllVouchers() throws VoucherNotFoundException{
        	List<VoucherRequest> list =	vservice.getAllVoucherRequest();
        	
        	return new ResponseEntity<List<VoucherRequest>>(list,HttpStatus.OK);
    }
    
    @GetMapping("/allAssignedVoucher") //get request to view all assigned vouchers
    public ResponseEntity<List<VoucherRequest>> getAllAssignedVoucher() throws NoVoucherPresentException
    {
    	List<VoucherRequest> list = vservice.getAllAssignedVoucherRequest();
    	return new ResponseEntity<List<VoucherRequest>>(list,HttpStatus.OK);
    }
    
    @GetMapping("/allUnAssignedVoucher") //get request to view all unassigned vouchers
    public ResponseEntity<List<VoucherRequest>> getAllUnAssignedVoucher() throws NoVoucherPresentException
    {
    	List<VoucherRequest> list = vservice.getAllNotAssignedVoucherRequest();
    	return new ResponseEntity<List<VoucherRequest>>(list,HttpStatus.OK);
    }

    @GetMapping("/getAllCompletedVoucherRequests")
    public ResponseEntity<List<VoucherRequest>> getAllCompletedVoucherRequests() throws NoCompletedVoucherRequestException
    {
    	List<VoucherRequest> list = vservice.getAllCompletedVoucherRequest();
    	return new ResponseEntity<List<VoucherRequest>>(list,HttpStatus.OK);
    }
    
    @GetMapping("/sendPendingEmails")
    public ResponseEntity<List<String>> pendingEmails() {
    	List<String> pendingEmails = vservice.pendingEmails();
    	
    	
    	return new ResponseEntity<List<String>>(pendingEmails, HttpStatus.OK);
    }
    @GetMapping("/pendingResultRequests")
    public ResponseEntity<List<VoucherRequest>> pendingRequests() {
    	List<VoucherRequest> pendingRequests = vservice.pendingRequests();
    	return new ResponseEntity<List<VoucherRequest>>(pendingRequests, HttpStatus.OK);
    }
}
