package com.va.voucher_request.model;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("requests")
public class VoucherRequest {
	
	@MongoId
	private String id;
	
//    @Pattern(regexp = "^[a-zA-Z]+$", message = "CandiLocalDate Name must contain only characters (a-z, A-Z)")
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
