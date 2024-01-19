package com.voucher.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VoucherRequest {
	
	private String id;
	

	private String candidateName;
	
	private String candidateEmail;
  private String cloudPlatform;
  private String cloudExam;
  private int doSelectScore;
  private String doSelectScoreImage;
  private String voucherCode;
  private LocalDate voucherIssueLocalDate;
  private LocalDate voucherExpiryLocalDate;
  private LocalDate plannedExamDate;
  private String examResult;

}
