package com.learning.entity;
/*
 * @author Dick
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="approvers")
public class Approver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min=5, max=255)
	@Column(name="fullName")
	private String fullName;
	private String passWord;

	public Approver() {
		super();
	}

	public Approver(long id, String fullName, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.passWord = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
  public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "Approver [id=" + id + ", fullName=" + fullName + ", passWord=" + passWord + "]";
	}
  
}
