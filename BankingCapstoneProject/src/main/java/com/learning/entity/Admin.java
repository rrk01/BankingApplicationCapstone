package com.learning.entity;

public class Admin {
	private String userName;
	private String passWord;
	private String fullName;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String userName, String passWord, String fullName) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		return "Admin [userName=" + userName + ", passWord=" + passWord + ", fullName=" + fullName + "]";
	}
	
	
}
