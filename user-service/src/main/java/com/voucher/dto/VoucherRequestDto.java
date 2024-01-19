package com.voucher.dto;

import java.time.LocalDate;

import lombok.Data;

@Data

public class VoucherRequestDto {

	private String id;

	private String candidateName;

	private String candidateEmail;

	private String cloudPlatform;

	private String cloudExam;

	private int doSelectScore;

	private String doSelectScoreImage;

	private LocalDate plannedExamDate;

}
