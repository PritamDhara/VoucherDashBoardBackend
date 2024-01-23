package com.voucher.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.client.VoucherRequestClient;
import com.voucher.dto.VoucherRequest;
import com.voucher.dto.VoucherRequestDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/requests")
@EnableFeignClients(basePackages = "com.*")
@CrossOrigin("*")
public class VoucherRequestClientController {
	
	@Autowired
	VoucherRequestClient voucherReqClient;
	
	@PostMapping("/voucher")
	@PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<VoucherRequest> requestVoucher(@RequestBody VoucherRequestDto request){
		return voucherReqClient.requestVoucher(request);
	}
	
    @GetMapping("/{candidateEmail}")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<List<VoucherRequest>> getAllVouchersByCandidateEmail(@PathVariable String candidateEmail){
    	return voucherReqClient.getAllVouchersByCandidateEmail(candidateEmail);
    }
    
    @PutMapping("/updateExamDate/{voucherCode}/{newExamDate}")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
	public ResponseEntity<VoucherRequest> updateExamDate(@PathVariable String voucherCode,@PathVariable LocalDate newExamDate){
    	return voucherReqClient.updateExamDate(voucherCode, newExamDate);
    }
    
		

    @PutMapping("/{voucherCode}/{newExamResult}")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<VoucherRequest> updateResultStatus( @PathVariable String voucherCode, @PathVariable String newExamResult){
    	return voucherReqClient.updateResultStatus(voucherCode, newExamResult);
    }
    
    @GetMapping("/assignvoucher/{voucherId}/{emailId}/{voucherrequestId}")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<VoucherRequest> assignVoucher(@PathVariable String voucherId,@PathVariable String emailId,@PathVariable String voucherrequestId){
    	return voucherReqClient.assignVoucher(voucherId, emailId, voucherrequestId);
    }
    
    @GetMapping("/getAllVouchers")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<List<VoucherRequest>> getAllVouchers(){
    	return voucherReqClient.getAllVouchers();
    }
    
    @GetMapping("/allAssignedVoucher")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<List<VoucherRequest>> getAllAssignedVoucher(){
    	return voucherReqClient.getAllAssignedVoucher();
    }
    
    @GetMapping("/allUnAssignedVoucher")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    public ResponseEntity<List<VoucherRequest>> getAllUnAssignedVoucher(){
    	return voucherReqClient.getAllUnAssignedVoucher();
    }
    
    @GetMapping("/getAllCompletedVoucherRequests")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<VoucherRequest>> getAllCompletedVoucherRequests(){
    	return voucherReqClient.getAllCompletedVoucherRequests();
    }
    
    @SecurityRequirement(name = "api")
	@GetMapping("/sendPendingEmails")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<String>> pendingEmails() {
		return  voucherReqClient.pendingEmails();
	}
	
	@SecurityRequirement(name = "api")
	@GetMapping("/pendingResultRequests")
	@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<VoucherRequest>> pendingRequests() {
		return voucherReqClient.pendingRequests();
	}

}
