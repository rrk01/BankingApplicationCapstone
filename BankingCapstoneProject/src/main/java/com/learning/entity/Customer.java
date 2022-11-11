package com.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "customers")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
	/*
	 * id: Number,
	 * username:String, UNIQUE 
	 * fullname:String,
	 * phone: String,
	 * SSN: int,
	 * password: String 
	 * role: enum RoleType (CUSTOMER, STAFF, ADMIN)
	 */
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//@NotBlank(message = "SSN CANNOT BE BLANK") /////////////////////////////////unknown errors when register method is invoked
	//@Size(min = 9, max = 9) @size can only be used on string or collection ect.

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
	@Column(name="status")
	private CustomerStatus status;
	public Customer() {
		super();
	}

	public Customer(long id, @NotBlank(message = "SSN CANNOT BE BLANK") @Size(min = 9, max = 9) long ssn,
			String userName, @NotBlank(message = "FULLNAME CANNOT BE EMPTY!") String fullName, String password,
			@Size(min = 8, max = 12, message = "PHONE NUMBER CAN BE 8-12 DIGITS") String phone, String secretQuestion,
			String secretAnswer, CustomerStatus status) {
		super();
		this.id = id;
		this.ssn = ssn;
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.phone = phone;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.status=status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getSSN() {
		return this.ssn;
	}

	public void setSSN(int sSN) {
		this.ssn = sSN;
	}
	public CustomerStatus getStatus() {
		return this.status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	
}