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
	private long id;
	private String name;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userName;
	private String password;
	private StaffStatus status;
	public Staff() {
		super();
	}
	public Staff(long id, String firstName, String userName, String password, StaffStatus status) {
		super();
		this.setId(id);
		this.name = firstName;
		this.userName = userName;
		this.password = password;
		this.setStatus(status);
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
	public void setName(String firstName) {
		this.name = firstName;
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
