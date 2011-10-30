package com.insta.fjee.library.core.service.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginAlreadyExistException;
import com.insta.fjee.library.core.service.ISubscriberService;

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
		return userBO.authentificate(login, password);
	}
	
	/**
	 * @See {@link ISubscriberService}
	 */
	@Override
	public UserDTO updateUser(UserDTO userDTO) throws EntityNotFoundException, LoginAlreadyExistException
	{
		return userBO.updateUser(userDTO);
	}

//	@Override
//	public boolean rentBook(UserDTO userDTO, String isbn) 
//	{
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean returnBook(BookDTO bookDTO) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
