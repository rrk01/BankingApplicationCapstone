package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table
public class Staff {///
	public enum StaffStatus{
		ENABLED,DISABLED
	}
	private String firstName;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userName;
	private String password;
	private StaffStatus status;
	public Staff() {
		super();
	}
	public Staff(String firstName, String userName, String password, StaffStatus status) {
		super();
		this.firstName = firstName;
		this.userName = userName;
		this.password = password;
		this.setStatus(status);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public StaffStatus getStatus() {
		return status;
	}
	public void setStatus(StaffStatus status) {
		this.status = status;
	}
	
}
