package com.insta.fjee.library.core.bo;

import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginAlreadyExistException;
import com.insta.fjee.library.core.exception.LoginInvalidException;

/**
 * User business object (BO) interface.
 * 
 * @author julien
 *
 */
public interface IUserBO
{
	/**
	 * 	Authenticate a user
	 * 
	 * @param login - login of the user
	 * @param password - password of the user
	 * @return
	 */
	public UserDTO authentificate(String login, String password);
	
	/**
	 * 	Authentificate a admin
	 * 
	 * @param login - login of the admin
	 * @param password - password of the admin
	 * @return
	 */
	public UserDTO authentificateAdmin(String login, String password);
	
	/**
	 * 	Create a new user
	 * 
	 * @param user - new user
	 * @return user created
	 * @throws LoginAlreadyExistException 
	 */
	public UserDTO createUser(UserDTO userDTO) throws LoginAlreadyExistException;
	
	/**
	 * 	Update a user
	 * 
	 * @param userDTO - user to update
	 * @return user updated
	 * @throws EntityNotFoundException
	 * @throws LoginAlreadyExistException
	 */
	public UserDTO updateUser(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException;

	/**
	 * 	Delete a user (for admin)
	 * 
	 * @param userDTO - user to delete
	 * @throws EntityNotFoundException
	 */
	public void deleteUser(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException;
	
	/**
	 * 	Check if a user is admin
	 * 
	 * @return true if admin
	 */
	public boolean isAdmin(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException;
}
