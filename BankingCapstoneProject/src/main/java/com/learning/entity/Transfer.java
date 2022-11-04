package com.learning.entity;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transfers")
public class Transfer { // CUSTOMER(OR)Staff TRANSFERS from type to type.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long fromAccount;
	private long toAccount;
	private BigDecimal amount;
	private String t_reason;
	private String transfer_by;
	
	public Transfer() {
		super();
	}

	public Transfer(long id, long fromAccount, long toAccount, BigDecimal amount, String t_reason, String transfer_by) {
		super();
		this.id = id;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.t_reason = t_reason;
		this.transfer_by = transfer_by;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public long getToAccount() {
		return toAccount;
	}

	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getT_reason() {
		return t_reason;
	}

	public void setT_reason(String t_reason) {
		this.t_reason = t_reason;
	}

	public String getTransfer_by() {
		return transfer_by;
	}

	public void setTransfer_by(String transfer_by) {
		this.transfer_by = transfer_by;
	}
	
}