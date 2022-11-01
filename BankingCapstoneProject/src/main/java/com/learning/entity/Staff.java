package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
@Entity
@Table
public class Staff {
	private String firstName;
	@Id
	private String userName;
	private String password;
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(String firstName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.userName = userName;
		this.password = password;
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
	
}