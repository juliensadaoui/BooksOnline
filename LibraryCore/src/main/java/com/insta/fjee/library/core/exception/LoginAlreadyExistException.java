package com.insta.fjee.library.core.exception;

public class LoginAlreadyExistException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2841428163014421117L;
	
	private String login;
	
	public LoginAlreadyExistException(String login)
	{
		super("The login \"" + login + "\" already exist.");
		this.login = login;
	}
	
	public String getLogin()
	{
		return login;
	}
}
