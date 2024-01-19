package com.voucherservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.voucherservice.dto.VoucherRequest;
import com.voucherservice.errordecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:8085/requests",name = "voucher-request",configuration = CustomErrorDecoder.class)
public interface VoucherRequestClient {

	 @GetMapping("/{candidateEmail}")
	    public ResponseEntity<List<VoucherRequest>> getAllVouchersByCandidateEmail(@PathVariable String candidateEmail);
}
