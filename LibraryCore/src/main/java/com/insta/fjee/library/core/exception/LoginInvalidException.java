package com.insta.fjee.library.core.exception;

public class LoginInvalidException extends Exception
{

	/**
	 * 	Serial Version Id
	 */
	private static final long serialVersionUID = 502726983529705518L;

	private String login;
	
	public LoginInvalidException(String login)
	{
		super("The login \"" + login + "\" is invalid.");
		this.login = login;
	}
	
	public String getLogin()
	{
		return login;
	}
}
