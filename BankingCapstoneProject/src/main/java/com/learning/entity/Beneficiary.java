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
	public enum accounttype{
		SB,CA
	}
	enum BeneficiaryStatus{
		ACTIVE,INACTIVE
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long beneficiaryAcNo;
	private long accountNumber; // CustomerId 
	private accounttype accountType; // SB OR CA
	private String beneficiaryName; // bene Name
	private Boolean approved; // false as Default
	private Date beneficiaryAddedDate;
	private BeneficiaryStatus status;
	
	public Beneficiary() {
		super();
	}

	public Beneficiary(long beneficiaryAcNo, long accountNumber, accounttype accountType, String beneficiaryName,
			Boolean approved, Date beneficiaryAddedDate,BeneficiaryStatus status) {
		super();
		this.beneficiaryAcNo = beneficiaryAcNo;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.beneficiaryName = beneficiaryName;
		this.approved = approved;
		this.beneficiaryAddedDate = beneficiaryAddedDate;
		this.setStatus(status);
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

	public BeneficiaryStatus getStatus() {
		return status;
	}

	public void setStatus(BeneficiaryStatus status) {
		this.status = status;
	}
	
	
}
