package com.insta.fjee.library.core.service.ws;

import javax.jws.WebService;

import com.insta.fjee.library.core.service.ISubscriberService;
import com.insta.fjee.library.stock.service.BookDTO;

//@WebService
public class SubscriberService implements ISubscriberService
{

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
