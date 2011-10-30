package com.insta.fjee.library.core.dto;

/**
 * 	Objet de l'utilisateur envoyé à travers les services web
 * 
 * @author julien
 *
 */
public class UserDTO
{
	private int ID;

	private String login;
	
	private String password;
	
	private String firstName;
	
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

	@Override
	public String toString() {
		return "UserDTO [ID=" + ID + ", login=" + login + ", password="
				+ password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
}
