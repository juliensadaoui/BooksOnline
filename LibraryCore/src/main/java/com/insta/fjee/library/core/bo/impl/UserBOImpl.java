package com.insta.fjee.library.core.bo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dao.IUserDAO;
import com.insta.fjee.library.core.util.CustomWSSupport;
import com.insta.fjee.library.stock.service.BookDTO;

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
	
	
	
	@Override
	public String getName(String isbn) {
		BookDTO book;
		book = CustomWSSupport.getLibraryBean().findBookByISBN(isbn);
		return book.getName();	
	}

}
