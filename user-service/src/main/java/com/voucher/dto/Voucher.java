package com.voucher.dto;

import java.time.LocalDate;

import lombok.Data;

@Data

public class Voucher {
	
	private String id;
	private String cloudPlatform;
	private String examName;
	private String voucherCode;
	private LocalDate issuedDate;
	private LocalDate expiryDate;
	private String issuedTo;

}
