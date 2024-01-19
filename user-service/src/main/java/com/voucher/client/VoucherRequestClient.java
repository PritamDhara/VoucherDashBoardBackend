package com.voucher.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.voucher.dto.VoucherRequest;
import com.voucher.dto.VoucherRequestDto;
import com.voucher.errordecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:8081/requests",name = "voucher-request",configuration = CustomErrorDecoder.class)
public interface VoucherRequestClient {
	
	@PostMapping("/voucher")
    public ResponseEntity<VoucherRequest> requestVoucher(@RequestBody VoucherRequestDto request);
	
    @GetMapping("/{candidateEmail}")
    public ResponseEntity<List<VoucherRequest>> getAllVouchersByCandidateEmail(@PathVariable String candidateEmail);
    
    @PutMapping("/updateExamDate/{voucherCode}/{newExamDate}")
	public ResponseEntity<VoucherRequest> updateExamDate(@PathVariable String voucherCode,@PathVariable LocalDate newExamDate);
		

    @PutMapping("/{voucherCode}/{newExamResult}")
    public ResponseEntity<VoucherRequest> updateResultStatus( @PathVariable String voucherCode, @PathVariable String newExamResult);
    
    @GetMapping("/assignvoucher/{voucherId}/{emailId}/{voucherrequestId}")
    public ResponseEntity<VoucherRequest> assignVoucher(@PathVariable String voucherId,@PathVariable String emailId,@PathVariable String voucherrequestId);
    
    @GetMapping("/getAllVouchers")
    public ResponseEntity<List<VoucherRequest>> getAllVouchers();
    
    @GetMapping("/allAssignedVoucher")
    public ResponseEntity<List<VoucherRequest>> getAllAssignedVoucher();
    
    @GetMapping("/allUnAssignedVoucher")
    public ResponseEntity<List<VoucherRequest>> getAllUnAssignedVoucher();

}
