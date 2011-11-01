package com.insta.fjee.library.web.bean;

public class UserBean 
{
	private String login;
	
	private String password;
	
	private String verifyPassword;
	
	private String firstName;
	
	private String lastName;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserBean [login=" + login + ", password=" + password
				+ ", verifyPassword=" + verifyPassword + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
}
