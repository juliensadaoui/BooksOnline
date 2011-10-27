package com.insta.fjee.library.core.bo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dao.IUserDAO;
import com.insta.fjee.library.core.model.User;
import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.BookNotFoundException;
import com.insta.fjee.library.stock.service.EntityNotFoundException_Exception;

/**
 * 
 * Stock Business Object (BO))
 * 
 * Stock business object (BO) interface and implementation, it’s used to store
 * the project’s business function, the real database operations (CRUD) works 
 * should not involved in this class, instead it has a DAO (StockDao) class to do it.
 * 
 * @author julien
 *
 */
@Service("userBO")
public class UserBOImpl implements IUserBO
{
	/**
	 * 	Make this class as a bean “stockBo” in Spring Ioc container, 
	 * 	and autowire the stock dao class.
	 */
	@Autowired
	private IUserDAO userDAO;
	
	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	


}
