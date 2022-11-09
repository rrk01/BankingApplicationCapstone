package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="staff")
public class Staff {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String userName;
	private String password;
	private StaffStatus status=StaffStatus.DISABLED;
	
	public Staff() {
		super();
	}


	public Staff(long id, String name, String userName, String password, StaffStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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