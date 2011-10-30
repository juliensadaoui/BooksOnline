package com.insta.fjee.library.core.exception;

public class UserNotAdminException extends Exception 
{
	/**
	 * 	Serial version id
	 */
	private static final long serialVersionUID = 6264557785811134542L;
	
	private String login;
	
	public UserNotAdminException(String login)
	{
		super("The user identified by the login \"" + login + "\" is not adminstrator");
		this.login = login;
	}
	
	public String getLogin()
	{
		return login;
	}
}
