package com.insta.fjee.library.core.dao;

import com.insta.fjee.library.core.model.User;

public interface IUserDAO 
{
	/**
	 * 	Insert a new user in the database
	 * 
	 * @param newInstance - new user
	 * @return - identifier of the user
	 */
	void save(User user);
	
	/**
	 * 	Update a new user in the database
	 * 
	 * @param user - user
	 * @return - identifier of the user
	 */
	void update(User user);
	
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
	
	/**
	 * 	Read a user
	 * 
	 * @param login - login of the user
	 * @param password - password of the user
	 * @return
	 */
	User findByLoginAndPassword(String login, String password);
}
