package com.insta.fjee.library.core.bo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.stereotype.Service;

import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dao.IUserDAO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.UserAlreadyExistException;
import com.insta.fjee.library.core.model.User;
import com.insta.fjee.library.core.service.IUserService;
import com.insta.fjee.library.core.util.Conversion;
import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.BookNotFoundException;
import com.insta.fjee.library.stock.service.EntityNotFoundException_Exception;

/**
 * 
 * Stock Business Object (BO))
 * 
 * Stock business object (BO) interface and implementation, itâ€™s used to store
 * the projectâ€™s business function, the real database operations (CRUD) works 
 * should not involved in this class, instead it has a DAO (StockDao) class to do it.
 * 
 * @author julien
 *
 */
@Service("userBO")
public class UserBOImpl implements IUserBO
{
	/**
	 * 	Make this class as a bean â€œstockBoâ€� in Spring Ioc container, 
	 * 	and autowire the stock dao class.
	 */
	@Autowired
	private IUserDAO userDAO;
	
	private Conversion conv;
	
	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	/**
	 *  @throws UserAlreadyExistException 
	 * @throws EntityNotFoundException 
	 * @See {@link IUserBO}
	 */
	@Override
	public UserDTO createUser(UserDTO user) throws UserAlreadyExistException
	{
		if (userDAO.findByLogin(user.getLogin()) != null) 
		{
			throw new UserAlreadyExistException(user);
		}
		User result;
		try {
			result = conv.fromDTO(user);
		} catch (EntityNotFoundException e) {
			throw new UserAlreadyExistException(user);
		}
			
		result = userDAO.save(result);
		return user;
	}

	@Override
	public UserDTO updateUser(UserDTO user) 
	{
		User result = null;
		try 
		{
			result = conv.fromDTO(user);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();		
		}
		result = userDAO.update(result);
		return user;
	}
	


}
