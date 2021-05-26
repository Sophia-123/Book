package com.book.entity;

public class Account {
	private String username;
	private String password;
	private UserType userType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Account(String username, String password, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public Account() {
		super();
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}

}
