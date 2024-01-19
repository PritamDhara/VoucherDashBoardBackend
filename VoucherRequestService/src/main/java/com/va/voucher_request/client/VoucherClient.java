package com.va.voucher_request.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.va.voucher_request.dto.Voucher;
import com.va.voucher_request.errordecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:9091/voucher",name = "voucher-service",configuration = CustomErrorDecoder.class)
public interface VoucherClient {
	
	@GetMapping("/assignUserInVoucher/{voucherId}/{userEmail}")
	public ResponseEntity<Voucher> assignUserInVoucher(@PathVariable String voucherId,@PathVariable String userEmail);
	
	@GetMapping("/getVoucherById/{id}")
	public ResponseEntity<Voucher> getVoucherById(@PathVariable String id);

}
