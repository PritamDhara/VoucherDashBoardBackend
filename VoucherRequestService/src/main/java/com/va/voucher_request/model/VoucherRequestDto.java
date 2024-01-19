package com.va.voucher_request.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRequestDto {
	
	private String id;
	
    @NotBlank(message = "Candidate Name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Candidate Name must contain only characters (a-z, A-Z)")
	private String candidateName;
    
    @NotBlank(message = "Candidate Email is required")
    @Email(message = "Please Enter Correct Email Address...")
	private String candidateEmail;
    
    @NotBlank(message = "Please Enter the Cloud Platform Name")
    private String cloudPlatform;
    
    @NotBlank(message = "Please Enter the Cloud Exam Name")
    private String cloudExam;
    
    @NotBlank(message = "DoSelect Score is required")
    private int doSelectScore;
    
    @NotBlank
    private String doSelectScoreImage;
    
    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in yyyy-MM-dd format")
    private LocalDate plannedExamDate;
    
  
}
