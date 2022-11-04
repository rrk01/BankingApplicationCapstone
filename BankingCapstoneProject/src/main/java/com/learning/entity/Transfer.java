package com.learning.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transfers")
public class Transfer { // CUSTOMER TRANSFERS from type to type.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long fromAccount;
	private long toAccount;
	private String t_reason;
	private boolean approved;
	
	public Transfer() {
		super();
	}

	public Transfer(long id, long fromAccount, long toAccount, String t_reason, boolean approved) { //Change 'from' and 'to' variable names ??
		super();
		this.id = id;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.t_reason = t_reason;
		this.approved = approved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getFrom() {
		return fromAccount;
	}

	public void setFrom(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public long getTo() {
		return toAccount;
	}

	public void setTo(long toAccount) {
		this.toAccount = toAccount;
	}

	public String getReason() {
		return t_reason;
	}

	public void setReason(String t_reason) {
		this.t_reason = t_reason;
	}

	public boolean getIsApproved() {
		return approved;
	}

	public void setIsApproved(boolean approved) {
		this.approved = approved;
	}
	
}
