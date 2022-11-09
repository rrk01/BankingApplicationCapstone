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
	private long beneficiaryAcNo; // Id in the table
	private long accountNumber; // Links to the ACCOUNT ENTITY
	private long customerId; // LINKS TO THE CUSTOMER ENTITY
	private AccountType accountType; // SB OR CA

	private String beneficiaryName; // bene Name
	private boolean approved; // false as Default (Done by Staff)
	private boolean active; // (Yes/No) (Done by Customer)
	private Date beneficiaryAddedDate;
	
	public Beneficiary() {
		super();
	}

	public Beneficiary(long beneficiaryAcNo, long accountNumber, long customerID, AccountType accountType, String beneficiaryName,
			boolean approved, boolean active, Date beneficiaryAddedDate ) {
		super();
		this.beneficiaryAcNo = beneficiaryAcNo;
		this.accountNumber = accountNumber;
		this.customerId = customerID;
		this.accountType = accountType;
		this.beneficiaryName = beneficiaryName;
		this.approved = approved;
		this.beneficiaryAddedDate = beneficiaryAddedDate;
		this.active=active;
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

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

