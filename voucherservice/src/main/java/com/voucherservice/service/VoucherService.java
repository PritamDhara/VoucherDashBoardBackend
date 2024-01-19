package com.voucherservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.voucherservice.entity.Voucher;
import com.voucherservice.exception.DataIsNotInsertedException;
import com.voucherservice.exception.NoVoucherPresentException;
import com.voucherservice.exception.TheseDataIsAlreadyPresentException;

public interface VoucherService {
	
	List<Voucher> saveAllVouchers(MultipartFile file) throws IOException, DataIsNotInsertedException, TheseDataIsAlreadyPresentException;
	List<Voucher> getAllVouchers() throws NoVoucherPresentException;
	List<Voucher> getAllVoucherByExamName(String examName) throws NoVoucherPresentException;
	List<Voucher> getAllVoucherByCloudPlatform(String cloudPlatform) throws NoVoucherPresentException;
	List<Voucher> getAllExpiredVoucher() throws NoVoucherPresentException;
	Voucher getVoucherById(String id) throws NoVoucherPresentException;
	Voucher assignUserEmail(String voucherId,String userEmail) throws NoVoucherPresentException;
	
	String removeVoucherById(String id) throws NoVoucherPresentException;
	
	List<Voucher> getAllVoucherWhichAreAssignd() throws NoVoucherPresentException;
	
	List<Voucher> getAllVoucherWhichAreAssignedButNotUtilized() throws NoVoucherPresentException;
	
	
	
	

}
