package com.insta.fjee.library.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
public class User implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1207394745361766803L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	@Column(unique = true, nullable = false)
	private String login;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	
	private boolean admin;

	/**
	 * 	Return the identifier of the user
	 * 
	 * @return identifier of the user
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 	Set the identifier of the user
	 * 
	 * @param iD - identifier of the user
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * 	Return the login of the user
	 * 
	 * @return - login of the user
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * 	Set the login of the user
	 * 
	 * @param login -login of the user
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * 	Return the password of the user
	 * 
	 * @return - password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 	Set the password of the user
	 * 
	 * @param password - password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 	Return the firstname of the user
	 * 
	 * @return - firstname of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 	Set the firstname of the user
	 * 
	 * @param firstName - firstname of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 	Return the lastname of the user
	 * 
	 * @return - lastname of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 	Set the lastname of the user
	 * 
	 * @param lastName - lastname of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 	Check if the user is admin
	 * 
	 * @return true if admin
	 */
	public boolean isAdmin() 
	{
		return admin;
	}

	/**
	 * 
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
