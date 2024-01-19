package com.voucher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.voucher.client.VoucherClient;
import com.voucher.dto.Voucher;

@RestController
@RequestMapping("/voucher")
@EnableFeignClients(basePackages = "com.*")
@CrossOrigin("*")
public class VoucherClientController {
	
	@Autowired
	VoucherClient voucherClient;

	@PostMapping("/addVouchers")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> addAllVouchers(@RequestParam("file") MultipartFile file) {
		
		return voucherClient.addAllVouchers(file);
		
	}
	
	@GetMapping("/getAllVouchers")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Voucher>> getAllAvailableVouchers(){
		
		return voucherClient.getAllAvailableVouchers();
	}
	
	@GetMapping("/vouchersByExamName/{examName}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Voucher>> getAllVoucherByExamName(@PathVariable String examName){
		return voucherClient.getAllVoucherByExamName(examName);
	}
 
	@GetMapping("/getVoucherById/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Voucher> getVoucherById(@PathVariable String id){
		return voucherClient.getVoucherById(id);
	}
 
	@GetMapping("/getVoucherByCloudPlatform/{cloudPlatform}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Voucher>> getAllVoucherByCloudPlatform(@PathVariable String cloudPlatform){
		return voucherClient.getAllVoucherByCloudPlatform(cloudPlatform);
	}
	
	@GetMapping("/getAllExpiredVouchers")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Voucher>> getAllExpiredVoucher(){
		return voucherClient.getAllExpiredVoucher();
	}
	
	@GetMapping("/assignUserInVoucher/{voucherId}/{userEmail}")
	@PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
	public ResponseEntity<Voucher> assignUserInVoucher(@PathVariable String voucherId,@PathVariable String userEmail){
		return voucherClient.assignUserInVoucher(voucherId, userEmail);
	}
	
	@DeleteMapping("/assignUserInVoucher/{voucherId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteVoucher(@PathVariable String voucherId){
		return voucherClient.deleteVoucher(voucherId);
	}
}
