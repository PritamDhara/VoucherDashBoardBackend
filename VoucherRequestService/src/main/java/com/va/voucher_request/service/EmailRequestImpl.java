package com.va.voucher_request.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailRequestImpl {

	@Autowired
	JavaMailSender javaMailSender;

	@Value("${username}")
	String username;

	public String sendEmail(String toMail, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(username);
		message.setTo(toMail);
		message.setSubject(subject);
		message.setText(body);

		javaMailSender.send(message);

		return "mail send successfully";
	}

}
