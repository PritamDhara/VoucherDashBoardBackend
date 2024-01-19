package com.voucherservice.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document(collection = "vouchers")
public class Voucher {
	
	private String id;
	private String cloudPlatform;
	private String examName;
	private String voucherCode;
	private LocalDate issuedDate;
	private LocalDate expiryDate;
	private String issuedTo;
	
	public Voucher(String cloudPlatform, String examName, String voucherCode, LocalDate issuedDate, LocalDate expiryDate) {
		super();
		this.cloudPlatform = cloudPlatform;
		this.examName = examName;
		this.voucherCode = voucherCode;
		this.issuedDate = issuedDate;
		this.expiryDate = expiryDate;
	}
	
	

}
