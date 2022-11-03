package com.learning.entity;
/*
 * @author Dick
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transfers")
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long from;
	Long to;
	String reason;
	Boolean isApproved;
	
	public Transfer() {
		super();
	}

}

	public Transfer(Long id, Long from, Long to, String reason, Boolean isApproved) { //Change 'from' and 'to' variable names ??
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.reason = reason;
		this.isApproved = isApproved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
