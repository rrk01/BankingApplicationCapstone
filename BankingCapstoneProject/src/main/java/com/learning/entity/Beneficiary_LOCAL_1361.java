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
	private accounttype accountType; // SB OR CA
	private String beneficiaryName; // bene Name
	private String approved; // NO as Default
	private Date beneficiaryAddedDate;
	
	public Beneficiary() {
		super();
	}

	public Beneficiary(long beneficiaryAcNo, long accountNumber, accounttype accountType, String beneficiaryName,
			String approved, Date beneficiaryAddedDate) {
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

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public Date getBeneficiaryAddedDate() {
		return beneficiaryAddedDate;
	}

	public void setBeneficiaryAddedDate(Date beneficiaryAddedDate) {
		this.beneficiaryAddedDate = beneficiaryAddedDate;
	}
	
	
}
