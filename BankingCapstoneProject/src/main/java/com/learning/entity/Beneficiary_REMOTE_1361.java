package com.learning.entity;

import java.sql.Date;

import javax.persistence.Id;

public class Beneficiary {
	@Id 
	private long beneficiaryAcNo;
	private long accountNumber; // CustomerId 
	private accounttype accountType; // SB or CA
	private String beneficiaryName; // benef Name
	private Boolean approved; // (false) as Default
	private Date beneficiaryAddedDate; // Date get generated after its created similar to date.now()...
	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Beneficiary(long beneficiaryAcNo, long accountNumber, accounttype accountType, String beneficiaryName,
			Boolean approved, Date beneficiaryAddedDate) {
		super();
		this.beneficiaryAcNo = beneficiaryAcNo;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.beneficiaryName = beneficiaryName;
		this.approved = approved;
		this.beneficiaryAddedDate = beneficiaryAddedDate;
	}
	public long getBeneficiaryAcNo() {
		return beneficiaryAcNo;
	}
	public void setBeneficiaryAcNo(long beneficiaryAcNo) {
		this.beneficiaryAcNo = beneficiaryAcNo;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public accounttype getAccountType() {
		return accountType;
	}
	public void setAccountType(accounttype accountType) {
		this.accountType = accountType;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Date getBeneficiaryAddedDate() {
		return beneficiaryAddedDate;
	}
	public void setBeneficiaryAddedDate(Date beneficiaryAddedDate) {
		this.beneficiaryAddedDate = beneficiaryAddedDate;
	}

}