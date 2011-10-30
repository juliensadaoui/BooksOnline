package com.insta.fjee.library.core.service;

import java.util.List;

import javax.jws.WebService;


import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.UserNotAdminException;

/**
 * 	Service web de l'application accessible par les administrateurs.
 * 
 * @author julien
 *
 */
@WebService
public interface IAdminService 
{	
	/**
	 * 	Authentificate a admin
	 * 
	 * @param login - login of the admin
	 * @param password - password of the admin
	 * @return
	 */
	public UserDTO authentificate(String login, String password);
	
	/**
	 * 	Get all users
	 * 
	 * @param adminDTO - user admin
	 * @return all users
	 * @throws UserNotAdminException
	 */
	public List<UserDTO> getAllUser(UserDTO adminDTO) throws UserNotAdminException;
	
	/**
	 * 	Delete a user 
	 * 
	 * @param userDTO - user to deleted
	 * @param adminDTO - user admin
	 * @throws UserNotAdminException
	 * @throws EntityNotFoundException
	 */
	public void deleteUser(UserDTO userDTO, UserDTO adminDTO) throws UserNotAdminException, EntityNotFoundException;
//	
//	/**
//	 * 	Dele
//	 * 
//	 * @param userDTO
//	 */
//	public void deleteRentBook(Ren userDTO);
	
	
}
