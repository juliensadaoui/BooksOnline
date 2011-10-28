package com.insta.fjee.library.core.exception;

import com.insta.fjee.library.core.dto.UserDTO;

public class UserAlreadyExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -913774867458987279L;
	private UserDTO userDTO;
	
	public UserAlreadyExistException(UserDTO userDTO)
	{
		this.userDTO = userDTO;
	}
	
	public String getMessage()
	{
		return userDTO.toString();
	}
	
}
