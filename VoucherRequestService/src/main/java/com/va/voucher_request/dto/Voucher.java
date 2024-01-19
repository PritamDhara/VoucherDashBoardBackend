package com.va.voucher_request.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Voucher {


	private String id;
	private String cloudPlatform;
	private String examName;
	private String voucherCode;
	private LocalDate issuedDate;
	private LocalDate expiryDate;
	private String issuedTo;
	
	
	
}
