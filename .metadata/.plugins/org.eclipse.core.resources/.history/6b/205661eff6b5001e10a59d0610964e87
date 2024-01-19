package com.voucher.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document(collection = "users")
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private int userId;
	@NotNull
	@NotBlank
	private String userName;
	@Email(message = "Please Provide Correct Email")
	private String userEmail;
	@NotNull
	private String password;
	@Email(message = "Please Provide Correct Email")
	private String mentorEmail;
	private String role;
	
	
	public User(String userName, String userEmail, String password, String mentorEmail,
			String role) {
		
		this.userName = userName;
		this.userEmail = userEmail;
		this.mentorEmail = mentorEmail;
		this.password = password;
		this.role = role;
	}

	

}
