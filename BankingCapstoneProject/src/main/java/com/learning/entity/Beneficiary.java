package com.learning.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.learning.entity.Account;

@Entity
@Table
public class Beneficiary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long beneficiaryAcNo;
	private long accountNumber; // CustomerId 
	private AccountType accountType; // SB OR CA
	private String beneficiaryName; // bene Name
	private Boolean approved; // false as Default
	private Date beneficiaryAddedDate;
	
	public Beneficiary() {
		super();
	}

	public Beneficiary(long beneficiaryAcNo, long accountNumber, AccountType accountType, String beneficiaryName,
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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
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
