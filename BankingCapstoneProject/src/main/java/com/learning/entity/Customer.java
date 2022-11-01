package com.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "SSN CANNOT BE BLANK")
	@Size(min = 9, max = 9)
	@Column(name="ssn")
	private long ssn;
	@Column(name="user_name")

	private String userName;
	@NotBlank(message = "FULLNAME CANNOT BE EMPTY!")
	@Column(name="full_name")
	private String fullName;
	@Column(name="password")
	private String password;
	@Column(name="phone")
	@Size(min = 8, max = 12, message = "PHONE NUMBER CAN BE 8-12 DIGITS")
	private String phone;
	@Column(name="secret_question")
	private String secretQuestion;
	@Column(name="secret_answer")
	private String secretAnswer;

	
	public Customer() {
		super();
	}

	public Customer(long id, @NotBlank(message = "SSN CANNOT BE BLANK") @Size(min = 9, max = 9) long ssn,
			String userName, @NotBlank(message = "FULLNAME CANNOT BE EMPTY!") String fullName, String password,
			@Size(min = 8, max = 12, message = "PHONE NUMBER CAN BE 8-12 DIGITS") String phone, String secretQuestion,
			String secretAnswer) {
		super();
		this.id = id;
		this.ssn = ssn;

		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.phone = phone;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}	
}