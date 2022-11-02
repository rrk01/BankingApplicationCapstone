package com.learning.entity;

import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Table;



@Entity
@Table

public class Staff {///
	private String firstName;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userName;
	private String firstName;
	private String password;
	
	public Staff() {
		super();
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
