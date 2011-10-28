package com.insta.fjee.library.core.bo;

import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.UserAlreadyExistException;

public interface IUserBO
{
	/**
	 * 	Create a new user
	 * 
	 * @param user - new user
	 * @return user created
	 */
	public UserDTO createUser(UserDTO user) throws UserAlreadyExistException;
	public UserDTO updateUser(UserDTO user); 
}
