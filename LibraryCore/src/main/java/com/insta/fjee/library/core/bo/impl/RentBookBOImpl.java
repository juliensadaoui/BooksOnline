package com.insta.fjee.library.core.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.fjee.library.core.bo.IRentBookBO;
import com.insta.fjee.library.core.dao.IRentBookDAO;
import com.insta.fjee.library.core.util.Conversion;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.LibraryBeanService;

@Service("rentBookBO")
public class RentBookBOImpl implements IRentBookBO {
	
	@Autowired
	private IRentBookDAO rentBookDAO;
	
	@Autowired
	private LibraryBeanService libraryBeanService;
	
	@Autowired
	private Conversion conv;
	

	@Override
	public boolean rentBook(BookDTO bookDTO) 
	{
		String isbn = bookDTO.getIsbn();
		long exemplaryNumber = libraryBeanService.getLibraryBeanPort().getExemplaryNumber(isbn);
		return false;
	}

	@Override
	public boolean returnBook(BookDTO bookDTO) 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
