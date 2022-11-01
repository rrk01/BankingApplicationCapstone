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
	private accounttype accountType;
	private float accountBalance;
	private boolean approved;
	@Id
	private long accountNumber;
	private Date dateOfCreation;
	private long customerId;
	public Account(accounttype accountType, float accountBalance, boolean approved, long accountNumber, Date dateOfCreation,
			long customerId) {
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.approved = approved;
		this.accountNumber = accountNumber;
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
	public boolean getApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
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
