package com.learning.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
enum CardType{
	CREDIT,DEBIT
}
@Entity 
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long t_id;
	private long account_number; // This connects it to the Account. 
	private String t_reason ;// WHAT IS TRANSACTION NAME? CHANGE TO REASON?
	private BigDecimal t_amount;
	private Date t_date;
	private CardType credit_debit;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(Long t_id, String t_reason, BigDecimal t_amount, Date t_date, CardType credit_debit,
			Long account_number) {
		super();
		this.t_id = t_id;
		this.t_reason = t_reason;
		this.t_amount = t_amount;
		this.t_date = t_date;
		this.credit_debit = credit_debit;
		this.account_number = account_number;
	}
	public Long getT_id() {
		return t_id;
	}
	public void setT_id(Long t_id) {
		this.t_id = t_id;
	}
	public String getT_reason() {
		return t_reason;
	}
	public void setT_reason(String t_reason) {
		this.t_reason = t_reason;
	}
	public BigDecimal getT_amount() {
		return t_amount;
	}
	public void setT_amount(BigDecimal t_amount) {
		this.t_amount = t_amount;
	}
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	public CardType getCredit_debit() {
		return credit_debit;
	}
	public void setCredit_debit(CardType credit_debit) {
		this.credit_debit = credit_debit;
	}
	public Long getAccount_number() {
		return account_number;
	}
	public void setAccount_number(Long account_number) {
		this.account_number = account_number;
	}

}