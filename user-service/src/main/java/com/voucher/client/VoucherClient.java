package com.voucher.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.voucher.dto.Voucher;
import com.voucher.errordecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:9091/voucher",name = "voucher-service",configuration = CustomErrorDecoder.class)
public interface VoucherClient {
	
	@PostMapping("/addVouchers")
	public ResponseEntity<?> addAllVouchers(@RequestParam("file") MultipartFile file) ;
	
	@GetMapping("/getAllVouchers")
	public ResponseEntity<List<Voucher>> getAllAvailableVouchers();
	
	@GetMapping("/vouchersByExamName/{examName}")
	public ResponseEntity<List<Voucher>> getAllVoucherByExamName(@PathVariable String examName);
 
	@GetMapping("/getVoucherById/{id}")
	public ResponseEntity<Voucher> getVoucherById(@PathVariable String id);
 
	@GetMapping("/getVoucherByCloudPlatform/{cloudPlatform}")
	public ResponseEntity<List<Voucher>> getAllVoucherByCloudPlatform(@PathVariable String cloudPlatform);
	
	@GetMapping("/getAllExpiredVouchers")
	public ResponseEntity<List<Voucher>> getAllExpiredVoucher();
	
	@GetMapping("/assignUserInVoucher/{voucherId}/{userEmail}")
	public ResponseEntity<Voucher> assignUserInVoucher(@PathVariable String voucherId,@PathVariable String userEmail);
	
	@DeleteMapping("/assignUserInVoucher/{voucherId}")
	public ResponseEntity<String> deleteVoucher(@PathVariable String voucherId);

}
