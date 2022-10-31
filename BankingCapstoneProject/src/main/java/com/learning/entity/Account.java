package com.learning.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
enum accounttype{
	SB,CA
}
@Entity
@Table(name="accounts")
public class Account {

	@Id
	private long accountNumber;
	private accounttype accountType;
	private float accountBalance;
	private String approved; ///////////////// no by default
	/////acct status enum enabled disabled p3-4
	private Date dateOfCreation;
	private long customerId;
	public Account(long accountNumber, accounttype accountType, float accountBalance, String approved,
			Date dateOfCreation, long customerId) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.approved = "No";///////////////////////////////////
		this.dateOfCreation = dateOfCreation;
		this.customerId = customerId;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public accounttype getAccountType() {
		return accountType;
	}
	public void setAccountType(accounttype accountType) {
		this.accountType = accountType;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
}
