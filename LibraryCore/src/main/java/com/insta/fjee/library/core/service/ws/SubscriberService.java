package com.insta.fjee.library.core.service.ws;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.service.ISubscriberService;
import com.insta.fjee.library.stock.service.BookDTO;

@WebService
public class SubscriberService implements ISubscriberService
{
	@Autowired
	private IUserBO userBO;
	
	/**
	 *  @See {@link ISubscriberService}
	 */
	@Override
	public UserDTO authentificate(String login, String password) 
	{
		return userBO.authenticate(login, password);
	}

	@Override
	public boolean rentBook(BookDTO bookDTO) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnBook(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		return false;
	}



}
