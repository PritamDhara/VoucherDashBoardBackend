package com.voucherservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.voucherservice.entity.Voucher;
import com.voucherservice.exception.DataIsNotInsertedException;
import com.voucherservice.exception.GivenFileIsNotExcelFileException;
import com.voucherservice.exception.NoVoucherPresentException;
import com.voucherservice.exception.TheseDataIsAlreadyPresentException;
import com.voucherservice.helper.Excelhelper;
import com.voucherservice.service.VoucherService;

@RestController
@RequestMapping("/voucher")
@CrossOrigin("*")
public class VoucherController {
	
	@Autowired
	private VoucherService voucherService;
	
	@PostMapping("/addVouchers")
	public ResponseEntity<?> addAllVouchers(@RequestParam("file") MultipartFile file) throws  IOException, DataIsNotInsertedException, GivenFileIsNotExcelFileException, TheseDataIsAlreadyPresentException{
		if(Excelhelper.checkExcelFormat(file))
		{
		List<Voucher> list = voucherService.saveAllVouchers(file);
		return new ResponseEntity<List<Voucher>>(list,HttpStatus.OK);
		}else {
			throw new GivenFileIsNotExcelFileException();
		}
	}
	
	@GetMapping("/getAllVouchers")
	public ResponseEntity<List<Voucher>> getAllAvailableVouchers() throws NoVoucherPresentException
	{
		List<Voucher> vouchers = voucherService.getAllVouchers();
		return new ResponseEntity<List<Voucher>>(vouchers,HttpStatus.OK);
	}
	
	@GetMapping("/vouchersByExamName/{examName}")
	public ResponseEntity<List<Voucher>> getAllVoucherByExamName(@PathVariable String examName)
			throws NoVoucherPresentException {
		List<Voucher> vouchersByExamName = voucherService.getAllVoucherByExamName(examName);
		return new ResponseEntity<>(vouchersByExamName, HttpStatus.OK);
 
	}
 
	@GetMapping("/getVoucherById/{id}")
	public ResponseEntity<Voucher> getVoucherById(@PathVariable String id) throws NoVoucherPresentException {
		Voucher voucherById = voucherService.getVoucherById(id);
		return new ResponseEntity<>(voucherById, HttpStatus.OK);
 
	}
 
	@GetMapping("/getVoucherByCloudPlatform/{cloudPlatform}")
	public ResponseEntity<List<Voucher>> getAllVoucherByCloudPlatform(@PathVariable String cloudPlatform)
			throws NoVoucherPresentException {
		List<Voucher> vouchersByCloudPlatform = voucherService.getAllVoucherByCloudPlatform(cloudPlatform);
		return new ResponseEntity<>(vouchersByCloudPlatform, HttpStatus.OK);
	}
	
	@GetMapping("/getAllExpiredVouchers")
	public ResponseEntity<List<Voucher>> getAllExpiredVoucher() throws NoVoucherPresentException {
		List<Voucher> expiredVouchers = voucherService.getAllExpiredVoucher();
		return new ResponseEntity<>(expiredVouchers, HttpStatus.OK);
 
	}
	
	@GetMapping("/assignUserInVoucher/{voucherId}/{userEmail}")
	public ResponseEntity<Voucher> assignUserInVoucher(@PathVariable String voucherId,@PathVariable String userEmail) throws NoVoucherPresentException {
		Voucher voucher = voucherService.assignUserEmail(voucherId,userEmail);
		return new ResponseEntity<>(voucher, HttpStatus.OK);
 
	}
	
	@DeleteMapping("/deleteVoucher/{voucherId}")
	public ResponseEntity<String> deleteVoucher(@PathVariable String voucherId) throws NoVoucherPresentException {
		String msg = voucherService.removeVoucherById(voucherId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/getAllAssignedVoucher")
	public ResponseEntity<List<Voucher>> getAllAssigedVoucherThatAreNotExpiredByDate() throws NoVoucherPresentException
	{
		List<Voucher> list = voucherService.getAllVoucherWhichAreAssignd();
		return new ResponseEntity<List<Voucher>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getAllAssignedButNotUtilizedVoucher")
	public ResponseEntity<List<Voucher>> getAllVoucherWhichAreAssignedButNotUtilized() throws NoVoucherPresentException
	{
		List<Voucher> list = voucherService.getAllVoucherWhichAreAssignedButNotUtilized();
		return new ResponseEntity<List<Voucher>>(list,HttpStatus.OK);
	}
	
	
	

}
