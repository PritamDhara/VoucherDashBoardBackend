package com.va.voucher_request.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import com.va.voucher_request.client.VoucherClient;
import com.va.voucher_request.dto.Voucher;
import com.va.voucher_request.exceptions.NoVoucherPresentException;
import com.va.voucher_request.exceptions.NotFoundException;
import com.va.voucher_request.exceptions.ParticularVoucherIsAlreadyAssignedException;
import com.va.voucher_request.exceptions.ResourceAlreadyExistException;
import com.va.voucher_request.exceptions.ScoreNotValidException;
import com.va.voucher_request.exceptions.VoucherIsAlreadyAssignedException;
import com.va.voucher_request.exceptions.VoucherNotFoundException;
import com.va.voucher_request.model.VoucherRequest;
import com.va.voucher_request.model.VoucherRequestDto;
import com.va.voucher_request.repo.VoucherRequestRepository;

@Service
@EnableFeignClients(basePackages = "com.*")
public class VoucherReqServiceImpl implements VoucherReqService {

	@Autowired
	private VoucherRequestRepository vrepo;
	
	//voucherservice client
	@Autowired
	VoucherClient voucherClient;

	@Override
	public VoucherRequest requestVoucher(VoucherRequestDto request) throws ScoreNotValidException, ResourceAlreadyExistException {
		String userEmail = request.getCandidateEmail();
		String examName = request.getCloudExam();
		
	    // Check if a voucher for the same exam and candidate already exists
		boolean examExists = vrepo.existsByCloudExamAndCandidateEmail(examName, userEmail);
		
		
        // If candidate has requested for the same exam again, throw an exception
		if (examExists) {
			throw new ResourceAlreadyExistException(
					"You have already requested voucher for this particular exam");
		}
		VoucherRequest vreq = new VoucherRequest();
		if (request.getDoSelectScore() >= 80) {
			String requestID = UUID.randomUUID().toString();
			vreq.setId(requestID);
			vreq.setCandidateName(request.getCandidateName());
			vreq.setCandidateEmail(request.getCandidateEmail());
			vreq.setCloudExam(request.getCloudExam());
			vreq.setCloudPlatform(request.getCloudPlatform());
			vreq.setDoSelectScore(request.getDoSelectScore());
			vreq.setDoSelectScoreImage(request.getDoSelectScoreImage());
			vreq.setPlannedExamDate(request.getPlannedExamDate());
			vreq.setExamResult("Pending");
			vrepo.save(vreq);
			return vreq;
		} else {
			throw new ScoreNotValidException("doSelectScore should be >= 80 to issue a voucher.");
		}
	}

	@Override  //get all vouchers using candidate email
	public List<VoucherRequest> getAllVouchersByCandidateEmail(String candidateEmail) throws NotFoundException {
		List<VoucherRequest> vouchersByCandidate = vrepo.findByCandidateEmail(candidateEmail);

		if (vouchersByCandidate.isEmpty()) {
			throw new NotFoundException("No vouchers found for candidate email: " + candidateEmail);
		}

		return vouchersByCandidate;
	}

	@Override   //to update the exam date by the candidate
	public VoucherRequest updateExamDate(String voucherCode, LocalDate newExamDate) throws NotFoundException {
		VoucherRequest voucherRequest = vrepo.findByVoucherCode(voucherCode);
		if (voucherRequest != null) {
			voucherRequest.setPlannedExamDate(newExamDate);
			try {
				vrepo.save(voucherRequest);
				return voucherRequest;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error saving VoucherRequest");
			}
 
		} else {

			throw new NotFoundException("No voucher found for voucher code: " + voucherCode);

		}

	}

	@Override  //to update the exam exam by the candidate
	public VoucherRequest updateExamResult(String voucherCode, String newExamResult) throws NotFoundException {
		VoucherRequest voucherRequest = vrepo.findByVoucherCode(voucherCode);
		if (voucherRequest != null) {
			voucherRequest.setExamResult(newExamResult);
			try {
				vrepo.save(voucherRequest);
				//return statement
				return voucherRequest;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error saving VoucherRequest");
			}

		} else {
			throw new NotFoundException("No voucher found for voucher code: " + voucherCode);

		}

	}

	@Override //assign the voucher to the respective candidate 
	public VoucherRequest assignVoucher(String voucherId, String emailId,String voucherrequestId) throws VoucherNotFoundException, NotFoundException, VoucherIsAlreadyAssignedException, ParticularVoucherIsAlreadyAssignedException {
		
		Voucher voucher = voucherClient.getVoucherById(voucherId).getBody();
		
		if(voucher==null)
		{
			throw new VoucherNotFoundException();
		}
		
		if(voucher.getIssuedTo()!=null) //check if the voucher is already assigned 
		{
			throw new ParticularVoucherIsAlreadyAssignedException();
		}
		
		Optional<VoucherRequest> voucherRequest = vrepo.findById(voucherrequestId);
		if(voucherRequest.isEmpty())
		{
			throw new NotFoundException("Voucher Request is Not Found");
		}
		
		
		VoucherRequest request = voucherRequest.get();
		
		if(request.getVoucherCode()!=null)
		{
			throw new VoucherIsAlreadyAssignedException();
		}
		request.setVoucherCode(voucher.getVoucherCode());
		request.setVoucherExpiryLocalDate(voucher.getExpiryDate());
		request.setVoucherIssueLocalDate(LocalDate.now());
		
		voucherClient.assignUserInVoucher(voucherId, emailId);
		VoucherRequest v = vrepo.save(request);

		return v;
	}

	@Override //to view all the vouchers 
	public List<VoucherRequest> getAllVoucherRequest() throws VoucherNotFoundException {
		
		List<VoucherRequest> allRequest = vrepo.findAll();
		
		if(allRequest.isEmpty())
		{
			throw new VoucherNotFoundException();
		}
		
		return allRequest;
	}

	@Override //to view all the assigned vouchers 
	public List<VoucherRequest> getAllAssignedVoucherRequest() throws NoVoucherPresentException {
		List<VoucherRequest> allrequest = vrepo.findAll();
		
		List<VoucherRequest> assignedvouchers = new ArrayList<VoucherRequest>();
		
		if(allrequest.isEmpty())
		{
			throw new NoVoucherPresentException();
		}
		for(VoucherRequest v:allrequest)
		{
			if(v.getVoucherCode()!=null)
			{
				assignedvouchers.add(v);
			}
		}
		
		return assignedvouchers;
	}

	@Override // to view all the unused vouchers 
	public List<VoucherRequest> getAllNotAssignedVoucherRequest() throws NoVoucherPresentException {
		List<VoucherRequest> allrequest = vrepo.findAll();
		
		List<VoucherRequest> notassignedvouchers = new ArrayList<VoucherRequest>();
		
		if(allrequest.isEmpty())
		{
			throw new NoVoucherPresentException();
		}
		for(VoucherRequest v:allrequest)
		{
			if(v.getVoucherCode()==null)
			{
				notassignedvouchers.add(v);
			}
		}
		
		return notassignedvouchers;
	}

}
