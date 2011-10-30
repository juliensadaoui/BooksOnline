package com.insta.fjee.library.core.service.ws;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.insta.fjee.library.core.bo.IRentBookBO;
import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dto.RentBookDTO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginInvalidException;
import com.insta.fjee.library.core.exception.NotEnoughtExemplaryException;
import com.insta.fjee.library.core.service.ISubscriberService;
import com.insta.fjee.library.stock.service.BookNotFoundException_Exception;

/**
 * 	Implémentation du service web des abonnés
 * 		
 * 
 * @author julien
 *
 */
@WebService
public class SubscriberService implements ISubscriberService
{
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IRentBookBO rentBookBO;
	
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
	public UserDTO updateUser(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException
	{
		return userBO.updateUser(userDTO);
	}

	/**
	 * @See {@link ISubscriberService}
	 */
	@Override
	public RentBookDTO rentBook(UserDTO userDTO, String isbn)
			throws BookNotFoundException_Exception, NotEnoughtExemplaryException, EntityNotFoundException, LoginInvalidException
	{
		return rentBookBO.rentBook(userDTO, isbn);
	}

	/**
	 * @See {@link ISubscriberService}
	 */
	@Override
	public List<RentBookDTO> getAllRents(UserDTO userDTO)
			throws EntityNotFoundException, LoginInvalidException {
		return rentBookBO.getAllRents(userDTO);
	}

	/**
	 * @See {@link ISubscriberService}
	 */
	@Override
	public RentBookDTO returnBook(RentBookDTO rentBookDTO)
			throws EntityNotFoundException {
		return rentBookBO.returnBook(rentBookDTO);
	}
}
