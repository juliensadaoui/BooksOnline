package com.insta.fjee.library.core.dao;

import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.model.User;

public interface IUserDAO 
{
	/**
	 * 	Insert a new user in the database
	 * 
	 * @param newInstance - new user
	 * @return - identifier of the user
	 */
	User save(User user);
	
	/**
	 * 	Update a new user in the database
	 * 
	 * @param user - user
	 * @return - identifier of the user
	 */
	User update(User user);
	
	/**
	 * 	Delete a user in the database
	 * 
	 * @param user -  user
	 * @return - identifier of the user
	 */
	void delete(User user);
	
	/**
	 * 	Read a user
	 * 
	 * @param id - identifier of the user
	 * @return user of the database, null if not exists
	 */
	User find(Integer id);
	User findByLogin(String login);
	
	/**
	 * 	Read a user
	 * 
	 * @param login - login of the user
	 * @param password - password of the user
	 * @return
	 */
	User findByLoginAndPassword(String login, String password);
	
	/**
	 * 	Read a user with exception if not exist
	 * 
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	User findOrFail(Integer id) throws EntityNotFoundException;
}
