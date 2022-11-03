package com.learning.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
	/*
	 * accountType: enum (SAVINGS/CHECKING), accountStatus: enum (ENABLED,
	 * DISABLED), accountBalance: Number, approved: no, accountNumber: Number,
	 * dateOfCreation: date/time, customerId: Number
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;
	@Column(name = "customer_id")
	private long customerId;
	@Column(name = "account_type") // Fix this enum stuff, naming convention seems indecisive -
	private AccountType accountType;
	@Column(name = "account_status")
	private AccountStatus accountStatus;
	@Column(name = "account_balance")
	private BigDecimal accountBalance;
	@Column(name="approved")
	private boolean approved;

//	private accounttype accountType;
	@Column(name = "creation_date")
	private Date dateOfCreation;

	public Account() {
		super();
	}
  
	public Account(long accountNumber, long customerId, AccountType accountType, AccountStatus accountStatus,
			BigDecimal accountBalance, boolean approved, Date dateOfCreation) {
		super();

		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
		this.accountBalance = accountBalance;
		this.approved = approved;
		this.dateOfCreation = dateOfCreation;
    
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

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

}